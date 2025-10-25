import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { DigitalSignature } from './digital-signature.entity';
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

  // Tạo mới chữ ký số và ký hash server-side
  async createSignature(data: Partial<DigitalSignature>): Promise<DigitalSignature> {
    // Tạo dữ liệu cần ký
    const now = new Date();
    const signaturePayload = JSON.stringify({
      user_id: data.user_id,
      usage_id: data.usage_id,
      type: data.type,
      timestamp: now.toISOString(),
    });

    // Sinh hash SHA-256
    const hash = crypto.createHash('sha256').update(signaturePayload).digest('hex');

    // Lưu chữ ký với hash
    const signature = this.signatureRepo.create({
      ...data,
      signature_data: hash,
      signed_at: now,
    });
    const savedSignature = await this.signatureRepo.save(signature);

    // Cập nhật thời gian check-in / check-out trong usage_record
    const usageRecord = await this.usageRepo.findOne({ where: { usage_id: data.usage_id } });

    if (usageRecord) {
      if (data.type === 'checkin') {
        usageRecord.checkin_time = now;
      } else if (data.type === 'checkout') {
        usageRecord.checkout_time = now;
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
}
