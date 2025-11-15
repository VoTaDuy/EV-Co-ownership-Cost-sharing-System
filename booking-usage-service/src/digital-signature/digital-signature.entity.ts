import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, ManyToOne, JoinColumn, Unique } from 'typeorm';
import { UsageRecord } from '../usage/usage.entity';

export enum SignatureType {
  CHECKIN = 'checkin',
  CHECKOUT = 'checkout',
}

@Unique(['usage_id', 'type']) // 👈 Thêm dòng này
@Entity({ name: 'digital_signature' })
export class DigitalSignature {
  @PrimaryGeneratedColumn()
  signature_id: number;

  @Column({ type: 'int' })
  user_id: number;
  @Column({ type: 'int' })
  usage_id: number;

  @Column({
    type: 'enum',
    enum: SignatureType,
  })
  type: SignatureType;

  @Column({ type: 'text', nullable: true })
  signature_data: string;

  @CreateDateColumn({ type: 'timestamp', precision: 6, default: () => 'CURRENT_TIMESTAMP(6)' })
  signed_at: Date;

  // relation -> usage_record (many signatures may exist for a usage, but
  // in your flow you'll likely have one checkin + one checkout)
  @ManyToOne(() => UsageRecord, (usage) => usage.digitalSignatures, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'usage_id' })
  usage: UsageRecord;
}