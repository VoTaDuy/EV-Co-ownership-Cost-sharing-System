import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn } from 'typeorm';

export enum AlertType {
  LATE_CHECKIN = 'LATE_CHECKIN',
  LATE_CHECKOUT = 'LATE_CHECKOUT',
}


@Entity({ name: 'alert_log' })
export class AlertLog {
  @PrimaryGeneratedColumn()
  alert_id: number;

  @Column({ type: 'int' })
  user_id: number;

  @Column({ type: 'enum', enum: AlertType })
  alert_type: AlertType;

  @Column({ type: 'text' })
  message: string;

  @CreateDateColumn({ type: 'timestamp', precision: 6, default: () => 'CURRENT_TIMESTAMP(6)' })
  created_at: Date;
}
