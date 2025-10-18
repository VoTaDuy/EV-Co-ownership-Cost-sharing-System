import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  BeforeInsert,
  CreateDateColumn,
  UpdateDateColumn,
  Unique,
} from 'typeorm';
import { v4 as uuidv4 } from 'uuid';
import { Vehicle } from '../vehicles/vehicles.entity';

@Entity('ownership_groups')
@Unique(['vehicle_id']) // 🔒 đảm bảo 1 xe chỉ có 1 nhóm
export class OwnershipGroup {
  @PrimaryGeneratedColumn('uuid')
  group_id: string;

  @Column()
  group_name: string;

  @Column()
  vehicle_id: string;

  @ManyToOne(() => Vehicle)
  @JoinColumn({ name: 'vehicle_id' })
  vehicle: Vehicle;

  @Column()
  created_by: string;

  @CreateDateColumn({ type: 'timestamp' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamp' })
  updated_at: Date;

  @BeforeInsert()
  generateId() {
    if (!this.group_id) this.group_id = uuidv4();
  }
}
