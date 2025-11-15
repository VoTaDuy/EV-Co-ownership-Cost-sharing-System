import { Injectable, NotFoundException, BadRequestException } from '@nestjs/common';
import { BookingRepository } from './booking.repository';
import { Booking } from './booking.entity';
import { ConflictLogService } from '../conflict_log/conflict-log.service';
import { BookingStatus } from './booking.entity';
import { UsageRepository } from '../usage/usage.repository';
import { HttpUserService } from '../common/http-user.service';
import { HttpAdminService } from '../common/http-admin.service';

@Injectable()
export class BookingService {
  constructor(
    private readonly bookingRepository: BookingRepository,
    private readonly conflictLogService: ConflictLogService,
    private readonly UsageRepository: UsageRepository,
    private readonly httpUserService: HttpUserService,
    private readonly httpAdminService: HttpAdminService,
  ) {}

  //Tạo booking
  async createBooking(data: Partial<Booking>): Promise<Booking> {

      if (!data.user_id) {
        throw new Error('Thiếu user_id');
      }

      if (!data.vehicle_id) {
        throw new Error('Thiếu vehicle_id');
      }

      if (!data.start_date || !data.end_date || !data.check_in_time || !data.check_out_time) {
        throw new Error('Thiếu thông tin bắt buộc: start_date, end_date, check_in_time, check_out_time');
      }

      if (new Date(data.start_date) >= new Date(data.end_date)) {
        throw new BadRequestException('start_date phải nhỏ hơn end_date');
      }

      const booking = await this.bookingRepository.createBooking(data);
      return booking;
    }

  // Kiểm tra conflict (trả về true/false)
    async checkBookingConflict(vehicle_id: number, start_date: Date, end_date: Date): Promise<boolean> {
      const existingBookings = await this.bookingRepository.findByVehicleAndDate(vehicle_id, start_date, end_date);
      return existingBookings.some((b) => {
        const newStart = new Date(start_date);
        const newEnd = new Date(end_date);
        const existingStart = new Date(b.start_date!);
        const existingEnd = new Date(b.end_date!);
        return newStart <= existingEnd && newEnd >= existingStart;
      });
    }

    // Tạo conflict log nếu có trùng
    async createConflictIfExists(booking: Booking): Promise<void> {
      const hasConflict = await this.checkBookingConflict(booking.vehicle_id, booking.start_date!, booking.end_date!);
      if (hasConflict) {
        await this.conflictLogService.createConflict(
          booking.user_id,
          booking.booking_id,
          `Phát hiện trùng lịch đặt xe cho vehicle_id ${booking.vehicle_id}`,
        );
      }
    }

    // Hàm tiện lợi: tạo booking + kiểm tra conflict liền mạch
    async createBookingWithCheckConflict(data: Partial<Booking>): Promise<Booking> {
      const booking = await this.createBooking(data);       // chỉ tạo booking
      await this.createConflictIfExists(booking);           // kiểm tra và tạo conflict nếu cần
      return booking;
    }


  //Lấy tất cả booking
  async getAllBookings(): Promise<Booking[]> {
    return this.bookingRepository.findAll();
  }

  //Lấy booking theo ID
  async getBookingById(id: number): Promise<Booking> {
    const booking = await this.bookingRepository.findById(id);
    if (!booking) throw new NotFoundException(`Booking ${id} không tồn tại`);
    return booking;
  }

  //Cập nhật booking
  async updateBooking(id: number, data: Partial<Booking>): Promise<Booking> {
  const existing = await this.getBookingById(id);
  if (!existing) throw new NotFoundException('Không tìm thấy booking');

  const updated = await this.bookingRepository.updateBooking(id, data);

  // Nếu booking vừa chuyển sang approved, tạo usage record
  if (
    existing.booking_status !== BookingStatus.APPROVED &&
    data.booking_status === BookingStatus.APPROVED
  ) {
    const usage = await this.UsageRepository.findByBookingId(updated.booking_id);
    if (!usage) {
      await this.UsageRepository.createUsage({
        booking_id: updated.booking_id,
        user_id: updated.user_id,
        vehicle_id: updated.vehicle_id,
        start_date: updated.start_date,
        end_date: updated.end_date,
      });
      console.log(` Đã tạo usage record cho booking ${updated.booking_id}`);
    }
  }
    return updated;
  }

  //Xóa booking
  async deleteBooking(id: number): Promise<void> {
    const existing = await this.getBookingById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy booking');
    return this.bookingRepository.deleteBooking(id);
  }
}
