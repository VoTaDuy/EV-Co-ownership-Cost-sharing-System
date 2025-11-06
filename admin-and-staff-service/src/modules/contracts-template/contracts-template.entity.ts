import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  CreateDateColumn,
  UpdateDateColumn,
} from 'typeorm';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';

@Entity('contracts_template')
export class ContractsTemplate {
  @PrimaryGeneratedColumn('uuid')
  contract_id: string;

  @Column()
  group_id: string;

  @Column({ nullable: true })
  template_id?: string;

  @Column({ nullable: true })
  contract_url?: string;

  @CreateDateColumn()
  created_at: Date;

  @UpdateDateColumn()
  updated_at: Date;

  @ManyToOne(() => OwnershipGroup, (group) => group.contracts, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'group_id' })
  group!: OwnershipGroup;
}
