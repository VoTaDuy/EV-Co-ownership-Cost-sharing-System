import { Injectable, NotFoundException } from '@nestjs/common';
import { UsageRepository } from './usage.repository';
import { UsageRecord } from './usage.entity';

@Injectable()
export class UsageService {
  constructor(private readonly usageRepository: UsageRepository) {}

  async createUsage(data: Partial<UsageRecord>): Promise<UsageRecord> {
    return this.usageRepository.createUsage(data);
  }

  async getAllUsage(): Promise<UsageRecord[]> {
    return this.usageRepository.findAll();
  }

  async getUsageById(id: string): Promise<UsageRecord> {
    const usage = await this.usageRepository.findById(id);
    if (!usage) throw new NotFoundException(`Usage record ${id} không tồn tại`);
    return usage;
  }

  async deleteUsage(id: string): Promise<void> {
    const existing = await this.getUsageById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy usage record');
    await this.usageRepository.deleteUsage(id);
  }
}
