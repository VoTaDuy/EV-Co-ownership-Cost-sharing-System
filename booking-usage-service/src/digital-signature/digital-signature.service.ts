import { Injectable, NotFoundException } from '@nestjs/common';
import { DigitalSignatureRepository } from './digital-signature.repository';
import { DigitalSignature } from './digital-signature.entity';
import { UsageRepository } from '../usage/usage.repository';
import { BookingRepository } from '../booking/booking.repository';
import { AlertService } from '../alert/alert.service';
import { AlertType } from '../alert/alert.entity';
import { SignatureType } from './digital-signature.entity'; 
import { HttpUserService } from '../common/http-user.service';

const LATE_THRESHOLD = 15; // phút

@Injectable()
export class DigitalSignatureService {
  constructor(
    private readonly signatureRepo: DigitalSignatureRepository,
    private readonly usageRepo: UsageRepository,
    private readonly bookingRepo: BookingRepository,
    private readonly alertService: AlertService,
    private readonly httpUserService: HttpUserService,
  ) {}

  // Tạo chữ ký số
  async createSignature(data: Partial<DigitalSignature>): Promise<DigitalSignature> {
    const signature = await this.signatureRepo.createSignature(data);

    if (!data.user_id) {
            throw new Error('Thiếu user_id');
          }
    
    const user = await this.httpUserService.getUserById(data.user_id!);
    if (!user) {
      throw new NotFoundException(`User ${data.user_id} không tồn tại`);
    }

    // Lấy usage + booking
    const usage = await this.usageRepo.findById(signature.usage_id);
    if (!usage) return signature;

    const booking = await this.bookingRepo.findById(usage.booking_id);
    if (!booking) return signature;

    // Kiểm tra nếu là check-in hoặc check-out
    const plannedTime = data.type === SignatureType.CHECKIN
      ? booking.check_in_time
      : booking.check_out_time;

    const dateBase = data.type === SignatureType.CHECKIN
      ? booking.start_date
      : booking.end_date;

    const planned = new Date(dateBase);
    const [hh, mm] = plannedTime.split(':');
    planned.setHours(+hh, +mm, 0, 0);

    const diff = (new Date(signature.signed_at).getTime() - planned.getTime()) / 60000;

    if (diff > LATE_THRESHOLD) {
      const type = data.type === SignatureType.CHECKIN ? AlertType.LATE_CHECKIN : AlertType.LATE_CHECKOUT;
      const msg = `Người dùng check-${data.type === SignatureType.CHECKIN ? 'in' : 'out'} muộn ${Math.floor(diff)} phút.`;
      await this.alertService.createAlert(booking.user_id, type, msg);
    }

    return signature;
  }


  // Lấy tất cả chữ ký
  async getAllSignatures(): Promise<DigitalSignature[]> {
    return this.signatureRepo.findAll();
  }

  // Lấy chữ ký theo ID
  async getSignatureById(id: number): Promise<DigitalSignature | null> {
    return this.signatureRepo.findById(id);
  }

  // Lấy chữ ký theo user_id
  async getSignaturesByUser(user_id: number): Promise<DigitalSignature[]> {
    return this.signatureRepo.findByUser(user_id);
  }
}
