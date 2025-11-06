import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn } from 'typeorm';

export enum AlertType {
  LATE_CHECKIN = 'LATE_CHECKIN',
  LATE_CHECKOUT = 'LATE_CHECKOUT',
}

export type AlertStatus = 'unread' | 'read' ;

@Entity({ name: 'alert_log' })
export class AlertLog {
  @PrimaryGeneratedColumn('uuid')
  alert_id: string;

  @Column({ type: 'varchar', length: 255 })
  user_id: string;

  @Column({ type: 'enum', enum: AlertType })
  alert_type: AlertType;

  @Column({ type: 'text' })
  message: string;

  @Column({ type: 'varchar', length: 20, default: 'unread' })
  status: AlertStatus;

  @CreateDateColumn({ type: 'timestamp', precision: 6, default: () => 'CURRENT_TIMESTAMP(6)' })
  created_at: Date;
}
