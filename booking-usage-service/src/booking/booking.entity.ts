import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn } from 'typeorm';

export enum BookingStatus {
  PENDING = 'pending',
  APPROVED = 'approved',
  REJECTED = 'rejected',
  COMPLETED = 'completed',
  CANCELLED = 'cancelled',
}

@Entity({ name: 'booking' })
export class Booking {
  @PrimaryGeneratedColumn('uuid')
  booking_id: string;

  @Column()
  user_id: string;

  @Column()
  vehicle_id: string;

  @Column({
    type: 'enum',
    enum: BookingStatus,
    default: BookingStatus.PENDING,
  })
  booking_status: BookingStatus;

  @Column({ type: 'date' })
  booking_date: Date;

  @Column({ type: 'time' })
  start_time: string;

  @Column({ type: 'time', nullable: true })
  end_time: string;

  @Column({ nullable: true })
  cancel_reason: string;

  @CreateDateColumn({ type: 'datetime' })
  created_at: Date;

  @UpdateDateColumn({ type: 'datetime' })
  updated_at: Date;
}
