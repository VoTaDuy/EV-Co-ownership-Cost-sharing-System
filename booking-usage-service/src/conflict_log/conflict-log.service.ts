import { Injectable, NotFoundException } from '@nestjs/common';
import { ConflictLogRepository } from './conflict-log.repository';
import { ConflictLog, ResolutionStatus } from './conflict-log.entity';
import { v4 as uuidv4 } from 'uuid';

@Injectable()
export class ConflictLogService {
  constructor(private readonly conflictRepo: ConflictLogRepository) {}

  // Tạo mới conflict log
  async createConflict(booking_id: string, description: string): Promise<ConflictLog> {
    const conflict = {
      conflict_id: uuidv4(),
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
  async getConflictById(id: string): Promise<ConflictLog | null> {
    return this.conflictRepo.findById(id);
  }

  // Lấy theo booking_id
  async getConflictsByBooking(booking_id: string): Promise<ConflictLog[]> {
    return this.conflictRepo.findByBooking(booking_id);
  }

  // Cập nhật trạng thái conflict
  async updateConflictStatus(
    conflict_id: string,
    status: ResolutionStatus,
    resolved_by?: string,
  ): Promise<ConflictLog> {
    const updated = await this.conflictRepo.updateStatus(conflict_id, status, resolved_by);
    if (!updated) {
      throw new NotFoundException(`Không tìm thấy conflict với ID: ${conflict_id}`);
    }
    return updated;
  }
}
