import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
  Unique,
  JoinColumn,
  Index,
  OneToMany,
} from 'typeorm';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';
import { EContract } from '../e-contract/e-contract.entity';
/* Dùng chung type cho group_role để tránh lỗi TypeORM save() */
export type GroupRole = 'admin' | 'member';
@Entity('group_members')
@Unique(['group_id', 'user_id']) // 1 user chỉ thuộc 1 group 1 lần
@Index(['group_id'])
@Index(['user_id'])
export class GroupMember {
  // Auto-increment primary key
  @PrimaryGeneratedColumn('increment')
  member_id: number;

  // Khóa ngoại: group_id → int (từ OwnershipGroup.group_id)
  @Column({ type: 'int' })
  group_id: number;

  // user_id: giả sử là int (từ bảng users có id tự tăng)
  @Column({ type: 'int' })
  user_id: number;

  // Vai trò: leader (trưởng nhóm) hoặc member
  @Column({
    type: 'enum',
    enum: ['admin', 'member'],
    default: 'member',
  })
  group_role: 'admin' | 'member';

  // Tỷ lệ sở hữu (0 - 100 hoặc 0.0 - 1.0)
  @Column({ type: 'float', default: 0 })
  ownership_ratio: number;

  // Thời gian tạo
  @CreateDateColumn()
  created_at: Date;

  // Quan hệ với OwnershipGroup (group_id là FK)
  @ManyToOne(() => OwnershipGroup, (group) => group.members, {
    onDelete: 'CASCADE', // Xóa group → xóa tất cả member
  })
  @JoinColumn({ name: 'group_id' })
  group: OwnershipGroup;

  // Quan hệ 1-n đến EContract
  @OneToMany(() => EContract, (contract) => contract.ownership_group)
  contracts: EContract[];
}
