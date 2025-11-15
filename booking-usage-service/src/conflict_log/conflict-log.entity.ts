import { Entity, PrimaryColumn, Column, CreateDateColumn, ManyToOne, JoinColumn } from 'typeorm';

import { Booking } from '../booking/booking.entity';

export enum ResolutionStatus {
  UNRESOLVED = 'unresolved',
  IN_PROGRESS = 'in_progress',
  RESOLVED = 'resolved',
  REJECTED = 'rejected',
}

@Entity({ name: 'conflict_log' })
export class ConflictLog {
  @PrimaryColumn({ type: 'int', generated: 'increment' })
  conflict_id: number;

  @Column({ type: 'int' })
  user_id: number;

  @Column({ type: 'int' })
  booking_id: number;

  @Column({ type: 'int', nullable: true })
  resolved_by: number;

  @Column({
    type: 'enum',
    enum: ResolutionStatus,
    default: ResolutionStatus.UNRESOLVED,
  })
  resolution_status: ResolutionStatus;

  @Column({ type: 'text', nullable: true })
  description: string;

  @CreateDateColumn({ type: 'timestamp', precision: 6, default: () => 'CURRENT_TIMESTAMP(6)'})
  created_at: Date;

  @Column({type: 'timestamp', precision: 6, nullable: true })
  resolved_at: Date;

  // Liên kết với bảng booking
  @ManyToOne(() => Booking)
  @JoinColumn({ name: 'booking_id' })
  booking: Booking;
}