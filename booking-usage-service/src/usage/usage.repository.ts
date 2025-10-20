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

  //Tạo mới 1 bản ghi sử dụng xe
  async createUsage(data: Partial<UsageRecord>): Promise<UsageRecord> {
    const usage = this.usageRepo.create(data);
    return this.usageRepo.save(usage);
  }

  //Lấy tất cả bản ghi sử dụng xe (có thể include booking nếu cần)
  async findAll(): Promise<UsageRecord[]> {
    return this.usageRepo.find({
      relations: ['booking'], 
    });
  }

  //Lấy 1 bản ghi theo ID
  async findById(id: string): Promise<UsageRecord | null> {
    return this.usageRepo.findOne({
      where: { usage_id: id },
      relations: ['booking'], // lấy luôn booking nếu cần
    });
  }

}
