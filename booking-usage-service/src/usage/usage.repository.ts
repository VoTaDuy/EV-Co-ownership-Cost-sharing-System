import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { UsageRecord } from './usage.entity';

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
  async findAll(): Promise<UsageRecord[]> {
    return this.usageRepo.find();
  }

  // Lấy bản ghi theo ID
  async findById(id: string): Promise<UsageRecord | null> {
    return this.usageRepo.findOne({ where: { usage_id: id } });
  }

  // Xóa bản ghi
  async deleteUsage(id: string): Promise<void> {
    await this.usageRepo.delete(id);
  }
}
