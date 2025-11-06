import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, OneToMany, JoinColumn, OneToOne } from 'typeorm';
import { Booking } from '../booking/booking.entity';
import { DigitalSignature } from '../digital-signature/digital-signature.entity';

@Entity({ name: 'usage_record' })
export class UsageRecord {
  @PrimaryGeneratedColumn('uuid')
  usage_id: string;

  @Column()
  booking_id: string;

  @Column()
  user_id: string;

  @Column()
  vehicle_id: string;

  @Column({ type: 'date' })
  start_date: Date;

  @Column({ type: 'date' })
  end_date: Date;

  @Column({ type: 'time', nullable: true })
  check_in_time: string | null;

  @Column({ type: 'time', nullable: true })
  check_out_time: string | null;

  @Column({ type: 'text', nullable: true })
  vehicle_condition: string | null;

  @Column({ type: 'float', nullable: true })
  distance: number | null;

  @CreateDateColumn({ type: 'datetime', name: 'record_time' })
  record_time: Date;

  @OneToOne(() => Booking)
  @JoinColumn({ name: 'booking_id' })
  booking: Booking;

  @OneToMany(() => DigitalSignature, (signature) => signature.usage)
  digitalSignatures: DigitalSignature[];
}
