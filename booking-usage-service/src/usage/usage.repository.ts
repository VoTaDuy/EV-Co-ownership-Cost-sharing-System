import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { UsageRecord } from './usage.entity';
import { GetAllUsageDto } from './dto/get-all-usage.dto';

@Injectable()
export class UsageRepository {
  constructor(
    @InjectRepository(UsageRecord)
    private readonly usageRepo: Repository<UsageRecord>,
  ) {}

  // Tạo mới một bản ghi sử dụng xe
  async createUsage(data: Partial<UsageRecord>): Promise<UsageRecord> {
    const usage = this.usageRepo.create(data);
    return this.usageRepo.save(usage);
  }

  // Lấy tất cả bản ghi
  async findAll(query?: GetAllUsageDto): Promise<UsageRecord[]> {
    const { user_id, booking_id, page = 1, limit = 20 } = query || {};
    const where: any = {};
    if (user_id) where.user_id = user_id;
    if (booking_id) where.booking_id = booking_id;

    return this.usageRepo.find({
      where,
      skip: (page - 1) * limit,
      take: limit,
      order: { record_time: 'DESC' },
    });
  }

  // Lấy bản ghi theo ID
  async findById(id: number): Promise<UsageRecord | null> {
    return this.usageRepo.findOne({ where: { usage_id: id } });
  }

  // Lấy bản ghi usage theo booking_id
  async findByBookingId(bookingId: number): Promise<UsageRecord | null> {
    return this.usageRepo.findOne({
      where: { booking_id: bookingId },
    });
  }

  async updateUsage(id: number, data: Partial<UsageRecord>): Promise<UsageRecord> {
    const formatted = { ...data };
    if (formatted.check_in_time && !formatted.check_in_time.includes(':'))
      formatted.check_in_time = `${formatted.check_in_time}:00`;
    if (formatted.check_out_time && !formatted.check_out_time.includes(':'))
      formatted.check_out_time = `${formatted.check_out_time}:00`;

    const existing = await this.usageRepo.findOneBy({ usage_id: id });
    if (!existing) throw new Error('Usage record not found');

    Object.assign(existing, formatted);
    await this.usageRepo.save(existing);
    return existing;
  }

  // Xóa bản ghi
  async deleteUsage(id: number): Promise<void> {
    if (!id) throw new Error('Usage ID is required');
    await this.usageRepo.delete({ usage_id: id });
  }
}
