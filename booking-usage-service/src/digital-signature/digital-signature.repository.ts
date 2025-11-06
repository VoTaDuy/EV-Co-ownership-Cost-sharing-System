import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { DigitalSignature, SignatureType } from './digital-signature.entity';
import { UsageRecord } from '../usage/usage.entity';
import * as crypto from 'crypto';

@Injectable()
export class DigitalSignatureRepository {
  constructor(
    @InjectRepository(DigitalSignature)
    private readonly signatureRepo: Repository<DigitalSignature>,

    @InjectRepository(UsageRecord)
    private readonly usageRepo: Repository<UsageRecord>,

  ) {}

  async createSignature(data: Partial<DigitalSignature>): Promise<DigitalSignature> {
    const now = new Date();

    // Giờ Việt Nam (Asia/Ho_Chi_Minh)
    const localTime = now.toLocaleTimeString('en-GB', { timeZone: 'Asia/Ho_Chi_Minh' });
    const localDateTime = new Date(
      new Date().toLocaleString('en-US', { timeZone: 'Asia/Ho_Chi_Minh' })
    );

    const signaturePayload = JSON.stringify({
      user_id: data.user_id,
      usage_id: data.usage_id,
      type: data.type,
      timestamp: localDateTime.toISOString(), // lưu UTC tương ứng của local time
    });

    const hash = crypto.createHash('sha256').update(signaturePayload).digest('hex');

    const signature = this.signatureRepo.create({
      ...data,
      signature_data: hash,
      signed_at: localDateTime, // -> lưu thời gian tương ứng với giờ VN
    });
    const savedSignature = await this.signatureRepo.save(signature);

    const usageRecord = await this.usageRepo.findOne({ where: { usage_id: data.usage_id } });
    if (usageRecord) {
      if (data.type === SignatureType.CHECKIN) {
        usageRecord.check_in_time = localTime; // Cập nhật thời gian check-in theo giờ VN
      } else if (data.type === SignatureType.CHECKOUT) {
        usageRecord.check_out_time = localTime;
      }
      await this.usageRepo.save(usageRecord);
    }

    return savedSignature;
  }

  // Lấy tất cả chữ ký
  async findAll(): Promise<DigitalSignature[]> {
    return this.signatureRepo.find();
  }

  // Lấy chữ ký theo ID
  async findById(id: string): Promise<DigitalSignature | null> {
    return this.signatureRepo.findOne({ where: { signature_id: id } });
  }

  // Lấy chữ ký theo user_id
  async findByUser(user_id: string): Promise<DigitalSignature[]> {
    return this.signatureRepo.find({ where: { user_id } });
  }

  async findCheckinSignature(usageId: string) {
    return this.signatureRepo.findOne({
      where: { usage_id: usageId, type: SignatureType.CHECKIN },
    });
  }

  async findCheckoutSignature(usageId: string) {
    return this.signatureRepo.findOne({
      where: { usage_id: usageId, type: SignatureType.CHECKOUT },
    });
  }
}
