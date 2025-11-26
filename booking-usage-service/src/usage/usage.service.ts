import { Injectable, NotFoundException } from '@nestjs/common';
import { UsageRepository } from './usage.repository';
import { UsageRecord } from './usage.entity';
import { GetAllUsageDto } from './dto/get-all-usage.dto';
import { StatsHelper } from '../common/stats.helper';

@Injectable()
export class UsageService {
  private readonly usageStats: StatsHelper<UsageRecord>; 
  constructor(private readonly usageRepository: UsageRepository) {
    this.usageStats = new StatsHelper<UsageRecord>(this.usageRepository['usageRepo']);
  }

  async createUsage(data: Partial<UsageRecord>): Promise<UsageRecord> {
    return this.usageRepository.createUsage(data);
  }

  async getAllUsage(query: GetAllUsageDto): Promise<UsageRecord[]> {
    return this.usageRepository.findAll(query);
  }

  async getUsageById(id: number): Promise<UsageRecord> {
    const usage = await this.usageRepository.findById(id);
    if (!usage) throw new NotFoundException(`Usage record ${id} không tồn tại`);
    return usage;
  }

  async getUsageByBookingId(bookingId: number): Promise<UsageRecord> {
    const usage = await this.usageRepository.findByBookingId(bookingId);
    if (!usage) throw new NotFoundException(`Không tìm thấy usage cho booking ${bookingId}`);
    return usage;
  }

  async updateUsage(id: number, data: Partial<UsageRecord>): Promise<UsageRecord> {
    const existing = await this.getUsageById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy usage record');
    return this.usageRepository.updateUsage(id, data);
  }

  async deleteUsage(id: number): Promise<void> {
    const existing = await this.getUsageById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy usage record');
    await this.usageRepository.deleteUsage(id);
  }

  async getTotalUsage(): Promise<number> {
    return this.usageStats.countTotal();
  }

  async getUsageByMonth(year: number): Promise<number[]> {
    return this.usageStats.countByMonthForUsage(year);
  }

  async getUsedDaysInMonth(
    user_id: number,
    vehicle_id: number,
    year: number,
    month: number,
  ): Promise<number> {
    const records = await this.usageRepository.getUsageInMonth(
      user_id,
      vehicle_id,
      year,
      month,
    );

    let totalDays = 0;

    for (const r of records) {
      const start = new Date(r.start_date);
      const end = new Date(r.end_date);

      // số ngày giữa start và end
      const diff = Math.ceil(
        (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)
      ) + 1; // +1 vì tính cả ngày bắt đầu

      totalDays += diff;
    }

    return totalDays;
  }
}
