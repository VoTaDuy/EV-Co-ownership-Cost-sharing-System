/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  JoinColumn,
  Index,
} from 'typeorm';
import {
  IsInt,
  Min,
  IsOptional,
  IsEnum,
  IsString,
  IsUrl,
  IsDate,
  IsNumber,
} from 'class-validator';
import { Expose } from 'class-transformer';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';

/**
 * ===================================================================
 * ENUM: Trạng thái chữ ký
 * ===================================================================
 */
export enum SignatureStatus {
  PENDING = 'pending',
  SIGNED = 'signed',
  REJECTED = 'rejected',
  EXPIRED = 'expired',
}

/**
 * ===================================================================
 * ENTITY: EContract
 * ===================================================================
 */
@Entity('e_contracts')
@Index(['ownership_group_id'])
@Index(['user_id'])
@Index(['signature_status'])
export class EContract {
  // Auto-increment int primary key
  @PrimaryGeneratedColumn('increment')
  contract_id: number;

  // FK: ownership_group_id → int
  @Column({ type: 'int', name: 'ownership_group_id' })
  ownership_group_id: number;

  // FK: user_id → int (giả sử bảng users dùng id tự tăng)
  @Column({ type: 'int' })
  user_id: number;

  // URL hợp đồng (PDF, Google Docs, v.v.)
  @Column({ type: 'text' })
  contract_url: string;

  // Trạng thái chữ ký
  @Column({
    type: 'enum',
    enum: SignatureStatus,
    default: SignatureStatus.PENDING,
  })
  signature_status: SignatureStatus;

  // Thời gian ký (null nếu chưa ký)
  @Column({ type: 'timestamp', nullable: true })
  signed_at: Date | null;

  // Thời gian tạo/cập nhật
  @CreateDateColumn()
  created_at: Date;

  @UpdateDateColumn()
  updated_at: Date;

  // Quan hệ: 1 nhóm sở hữu → nhiều hợp đồng
  @ManyToOne(() => OwnershipGroup, (group) => group.contracts, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'ownership_group_id' })
  ownership_group: OwnershipGroup;
}

/**
 * ===================================================================
 * DTO: Tạo mới E-Contract
 * ===================================================================
 */
export class CreateEContractDto {
  @IsInt()
  @Min(1)
  ownership_group_id: number;

  @IsInt()
  @Min(1)
  user_id: number;

  @IsString()
  @IsUrl({}, { message: 'contract_url must be a valid URL' })
  contract_url: string;

  @IsOptional()
  @IsEnum(SignatureStatus, {
    message: 'signature_status must be one of: pending, signed, rejected, expired',
  })
  signature_status?: SignatureStatus;
}

/**
 * ===================================================================
 * DTO: Cập nhật E-Contract (chỉ trạng thái + thời gian ký)
 * ===================================================================
 */
export class UpdateEContractDto {
  @IsOptional()
  @IsEnum(SignatureStatus)
  signature_status?: SignatureStatus;

  @IsOptional()
  @IsDate()
  signed_at?: Date | null;
}

/**
 * ===================================================================
 * DTO: Trả về cho client
 * ===================================================================
 */
export class EContractResponseDto {
  @IsInt()
  @Min(1)
  @Expose()
  contract_id: number;

  @IsInt()
  @Min(1)
  @Expose()
  ownership_group_id: number;

  @IsInt()
  @Min(1)
  @Expose()
  user_id: number;

  @IsString() 
  @IsUrl()
  @Expose()
  contract_url: string;

  @IsEnum(SignatureStatus)
  @Expose()
  signature_status: SignatureStatus;

  @IsOptional()
  @IsDate()
  @Expose()
  signed_at: Date | null;

  @IsDate()
  @Expose()
  created_at: Date;

  @IsDate()
  @Expose()
  updated_at: Date;
}