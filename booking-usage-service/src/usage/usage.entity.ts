import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, OneToMany, JoinColumn, OneToOne } from 'typeorm';
import { Booking } from '../booking/booking.entity';
import { DigitalSignature } from '../digital_signature/digital-signature.entity';

@Entity({ name: 'usage_record' })
export class UsageRecord {
  @PrimaryGeneratedColumn('uuid')
  usage_id: string;

  @Column()
  booking_id: string;

  @Column()
  user_id: string;

  @Column({ type: 'datetime', nullable: true })
  checkin_time: Date;

  @Column({ type: 'datetime', nullable: true })
  checkout_time: Date;

  @Column({ type: 'text', nullable: true })
  vehicle_condition: string;

  @Column({ type: 'float', nullable: true })
  distance: number;

  @CreateDateColumn({ type: 'datetime', name: 'record_time' })
  record_time: Date;

  @OneToOne(() => Booking)
  @JoinColumn({ name: 'booking_id' })
  booking: Booking;

  @OneToMany(() => DigitalSignature, (signature) => signature.usage)
  digitalSignatures: DigitalSignature[];
}
