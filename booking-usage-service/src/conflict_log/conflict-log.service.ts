import { Injectable, NotFoundException } from '@nestjs/common';
import { ConflictLogRepository } from './conflict-log.repository';
import { ConflictLog, ResolutionStatus } from './conflict-log.entity';

@Injectable()
export class ConflictLogService {
  constructor(private readonly conflictRepo: ConflictLogRepository) {}

  // Tạo mới conflict log
  async createConflict(user_id: number, booking_id: number, description: string): Promise<ConflictLog> {
    const conflict = {
      user_id,
      booking_id,
      description,
      resolution_status: ResolutionStatus.UNRESOLVED,
    };
    return this.conflictRepo.createConflict(conflict);
  }

  // Lấy tất cả conflict
  async getAllConflicts(): Promise<ConflictLog[]> {
    return this.conflictRepo.findAll();
  }

  // Lấy theo ID
  async getConflictById(id: number): Promise<ConflictLog | null> {
    return this.conflictRepo.findById(id);
  }

  // Lấy theo user ID
  async getConflictsByUser(user_id: number): Promise<ConflictLog[]> {
    return this.conflictRepo.findByUser(user_id);
  }

  // Cập nhật trạng thái conflict
  async updateConflictStatus(
    conflict_id: number,
    status: ResolutionStatus,
    resolved_by?: number,
  ): Promise<ConflictLog> {
    const updated = await this.conflictRepo.updateStatus(conflict_id, status, resolved_by);
    if (!updated) {
      throw new NotFoundException(`Không tìm thấy conflict với ID: ${conflict_id}`);
    }
    return updated;
  }
}
