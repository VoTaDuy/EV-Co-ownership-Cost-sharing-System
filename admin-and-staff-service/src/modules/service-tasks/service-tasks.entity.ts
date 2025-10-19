import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  BeforeInsert,
} from 'typeorm';
import { Vehicle } from '../vehicles/vehicles.entity';

@Entity('service_tasks')
export class ServiceTask {
  @PrimaryGeneratedColumn('uuid')
  task_id: string;

  @Column()
  vehicle_id: string;

  @ManyToOne(() => Vehicle)
  @JoinColumn({ name: 'vehicle_id' })
  vehicle: Vehicle;

  @Column({ nullable: true })
  assigned_to: string;

  @Column({
    type: 'enum',
    enum: ['maintenance', 'inspection', 'charging', 'cleaning', 'other'],
  })
  type: 'maintenance' | 'inspection' | 'charging' | 'cleaning' | 'other';

  @Column({ type: 'text', nullable: true })
  description: string;

  @Column({
    type: 'enum',
    enum: ['pending', 'in_progress', 'completed', 'cancelled'],
    default: 'pending',
  })
  status: 'pending' | 'in_progress' | 'completed' | 'cancelled';

  @Column({ type: 'timestamp', nullable: true })
  scheduled_at: Date;

  @Column({ type: 'timestamp', nullable: true })
  completed_at: Date;

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
  created_at: Date;

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
  updated_at: Date;

  @BeforeInsert()
  setDefaultDates() {
    this.created_at = new Date();
    this.updated_at = new Date();
  }
}
