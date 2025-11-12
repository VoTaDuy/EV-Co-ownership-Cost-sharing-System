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
  @PrimaryColumn({ type: 'varchar', length: 255 })
  conflict_id: string;

  @Column({ type: 'varchar', length: 255 })
  user_id: string;

  @Column({ type: 'varchar', length: 255 })
  booking_id: string;

  @Column({ type: 'varchar', length: 255, nullable: true })
  resolved_by: string;

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