import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { ConflictLog } from './conflict-log.entity';

@Injectable()
export class ConflictLogRepository {
  constructor(
    @InjectRepository(ConflictLog)
    private readonly conflictRepo: Repository<ConflictLog>,
  ) {}

  // Tạo conflict log mới
  async createConflict(data: Partial<ConflictLog>): Promise<ConflictLog> {
    const conflict = this.conflictRepo.create(data);
    return this.conflictRepo.save(conflict);
  }

  // Lấy tất cả conflict
  async findAll(): Promise<ConflictLog[]> {
    return this.conflictRepo.find();
  }

  // Lấy conflict theo ID
  async findById(id: string): Promise<ConflictLog | null> {
    return this.conflictRepo.findOne({ where: { conflict_id: id } });
  }

  // Lấy conflict theo booking_id
  async findByUser(user_id: string): Promise<ConflictLog[]> {
    return this.conflictRepo.find({ where: { user_id} });
  }

  // Cập nhật trạng thái conflict
  async updateStatus(
    conflict_id: string,
    status: string,
    resolved_by?: string,
  ): Promise<ConflictLog | null> {
    const conflict = await this.conflictRepo.findOne({ where: { conflict_id } });
    if (!conflict) return null;

    conflict.resolution_status = status as any;
    conflict.resolved_by = resolved_by ?? conflict.resolved_by;
    conflict.resolved_at = new Date();

    return this.conflictRepo.save(conflict);
  }
}
