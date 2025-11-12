import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
  OneToMany,
  Unique,
  JoinColumn,
} from 'typeorm';
import { EContact } from '../e-contract/e-contract.entity';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';

@Entity('group_members')
@Unique(['group_id', 'user_id']) // 🔒 1 user chỉ 1 lần trong 1 group
export class GroupMember {
  @PrimaryGeneratedColumn('uuid')
  member_id: string;

  @Column({ type: 'uuid' })
  group_id: string;

  @Column({ type: 'uuid' })
  user_id: string;

  @Column({ type: 'varchar', default: 'Co-owner' })
  group_role: string;

  @Column({ type: 'float', default: 0 })
  ownership_ratio: number;

  @CreateDateColumn()
  created_at: Date;

  // 🔗 Quan hệ với OwnershipGroup
  @ManyToOne(() => OwnershipGroup, (group) => group.members)
  @JoinColumn({ name: 'group_id' })
  group: OwnershipGroup;

  // 🔗 Quan hệ 1-n với EContact
  @OneToMany(() => EContact, (contact) => contact.member)
  contacts: EContact[];
}
