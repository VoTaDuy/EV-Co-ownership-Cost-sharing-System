import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  OneToMany,
} from 'typeorm';
import { EContact } from '../e-contract/e-contract.entity';

@Entity('group_members')
export class GroupMember {
  @PrimaryGeneratedColumn('uuid')
  member_id: string;

  @Column({ type: 'varchar' })
  user_id: string;

  @Column({ type: 'varchar' })
  group_id: string;

  @Column({ type: 'varchar', nullable: true })
  group_role: string;

  @Column({ type: 'decimal', precision: 5, scale: 2, nullable: true })
  ownership_ratio: number;

  @CreateDateColumn()
  created_at: Date;

  // Quan hệ 1-n với e_contacts
  @OneToMany(() => EContact, (contact) => contact.member)
  contacts: EContact[];
}
