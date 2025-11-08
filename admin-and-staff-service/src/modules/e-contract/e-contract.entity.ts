import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
  JoinColumn,
} from 'typeorm';
import { GroupMember } from '../group-members/group-members.entity';

@Entity('e_contacts')
export class EContact {
  @PrimaryGeneratedColumn('uuid')
  contract_id: string;

  @Column({ type: 'varchar' })
  vehicle_id: string;

  @Column({ type: 'varchar' })
  template_id: string;

  @Column({ type: 'varchar', nullable: true })
  signature_status: string;

  @Column({ type: 'timestamp', nullable: true })
  signed_at: Date;

  @CreateDateColumn()
  created_at: Date;

  // Khóa ngoại tới group_members
  @ManyToOne(() => GroupMember, (member) => member.contacts, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'member_id' })
  member: GroupMember;
}
