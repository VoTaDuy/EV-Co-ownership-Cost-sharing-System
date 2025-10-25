import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, ManyToOne, JoinColumn } from 'typeorm';
import { UsageRecord } from '../usage/usage.entity';

export enum SignatureType {
  CHECKIN = 'checkin',
  CHECKOUT = 'checkout',
}

@Entity({ name: 'digital_signature' })
export class DigitalSignature {
  @PrimaryGeneratedColumn('uuid')
  signature_id: string;

  @Column()
  user_id: string;

  @Column()
  usage_id: string;

  @Column({
    type: 'enum',
    enum: SignatureType,
  })
  type: SignatureType;

  @Column({ type: 'text', nullable: true })
  signature_data: string;

  @CreateDateColumn({ type: 'datetime' })
  signed_at: Date;

  // relation -> usage_record (many signatures may exist for a usage, but
  // in your flow you'll likely have one checkin + one checkout)
  @ManyToOne(() => UsageRecord, (usage) => usage.digitalSignatures, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'usage_id' })
  usage: UsageRecord;
}