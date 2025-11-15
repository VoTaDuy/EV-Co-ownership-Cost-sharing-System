import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  CreateDateColumn,
  UpdateDateColumn,
  Unique,
  OneToMany,
} from 'typeorm';

import { Vehicle } from '../vehicles/vehicles.entity';
import { GroupMember } from '../group-members/group-members.entity';
import { EContract } from '../e-contract/e-contract.entity';

@Entity('ownership_groups')
@Unique(['vehicle_id'])
export class OwnershipGroup {
  // 🔥 ID tự tăng (INT)
  @PrimaryGeneratedColumn()
  group_id: number;

  @Column()
  group_name: string;

  // 🔥 INT vì vehicle_id đã đổi sang INT
  @Column()
  vehicle_id: number;

  @ManyToOne(() => Vehicle)
  @JoinColumn({ name: 'vehicle_id' })
  vehicle: Vehicle;

  // Nếu user_id là UUID → giữ nguyên string
  @Column()
  created_by: number;

  @CreateDateColumn({ type: 'timestamp' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamp' })
  updated_at: Date;

  // Quan hệ 1-n đến GroupMember
  @OneToMany(() => GroupMember, (member) => member.group, {
    cascade: true,
  })
  members: GroupMember[];

  // Quan hệ 1-n đến EContract
  @OneToMany(() => EContract, (contract) => contract.ownership_group)
  contracts: EContract[];
}
