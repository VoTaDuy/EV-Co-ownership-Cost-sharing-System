/* eslint-disable @typescript-eslint/no-unsafe-return */
import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  CreateDateColumn,
  UpdateDateColumn,
} from 'typeorm';
import { Vehicle } from '../vehicles/vehicles.entity';

@Entity('report')
export class Report {
  @PrimaryGeneratedColumn('uuid')
  report_id: string;

  @Column()
  vehicle_id: string;

  @Column({ nullable: true })
  template_id?: string;

  @Column({ type: 'text', nullable: true })
  description?: string;

  @Column({ nullable: true })
  status?: string;

  @Column({ nullable: true })
  type?: string;

  @CreateDateColumn()
  created_at: Date;

  @UpdateDateColumn()
  updated_at: Date;

  @ManyToOne(() => Vehicle, (vehicle) => vehicle.reports, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'vehicle_id' })
  vehicle?: Vehicle;
}
