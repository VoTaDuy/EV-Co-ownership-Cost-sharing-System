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
  OneToMany,
} from 'typeorm';
import { v4 as uuidv4 } from 'uuid';
import { Vehicle } from '../vehicles/vehicles.entity';
import { ContractsTemplate } from '../contracts-template/contracts-template.entity';
import { GroupMember } from '../group-members/group-members.entity';

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

  // 🔗 Quan hệ 1-n với bảng group_members
  @OneToMany(() => GroupMember, (member) => member.group, { cascade: true })
  members: GroupMember[];

  // 🔗 Quan hệ 1-n với bảng contracts_template
  @OneToMany(() => ContractsTemplate, (contract) => contract.group)
  contracts!: ContractsTemplate[];
}
