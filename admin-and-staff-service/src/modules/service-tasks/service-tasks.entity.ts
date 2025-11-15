/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  CreateDateColumn,
  UpdateDateColumn,
  Index,
} from 'typeorm';
import {
  IsInt,
  Min,
  IsOptional,
  IsString,
  IsEnum,
  IsDate,
  MaxLength,
} from 'class-validator';
import { Expose } from 'class-transformer';
import { Vehicle } from '../vehicles/vehicles.entity';

/**
 * ===================================================================
 * ENUM: Loại công việc
 * ===================================================================
 */
export enum TaskType {
  MAINTENANCE = 'maintenance',
  INSPECTION = 'inspection',
  CHARGING = 'charging',
  CLEANING = 'cleaning',
  OTHER = 'other',
}

/**
 * ===================================================================
 * ENUM: Trạng thái công việc
 * ===================================================================
 */
export enum TaskStatus {
  PENDING = 'pending',
  IN_PROGRESS = 'in_progress',
  COMPLETED = 'completed',
  CANCELLED = 'cancelled',
}

/**
 * ===================================================================
 * ENTITY: ServiceTask
 * ===================================================================
 */
@Entity('service_tasks')
@Index(['vehicle_id'])
@Index(['status'])
@Index(['scheduled_at'])
export class ServiceTask {
  // Auto-increment int primary key
  @PrimaryGeneratedColumn('increment')
  task_id: number;

  // FK: vehicle_id → int (từ bảng vehicles)
  @Column({ type: 'int' })
  vehicle_id: number;

  // Quan hệ với Vehicle
  @ManyToOne(() => Vehicle, (vehicle) => vehicle.serviceTasks, {
    onDelete: 'CASCADE',
  })
  @JoinColumn({ name: 'vehicle_id' })
  vehicle: Vehicle;

  // Người được giao (user_id) – int hoặc string tùy hệ thống
  // Giả sử user_id là int (tự tăng)
  @Column({ type: 'int', nullable: true })
  assigned_to: number | null;

  // Loại công việc
  @Column({
    type: 'enum',
    enum: TaskType,
    default: TaskType.MAINTENANCE,
  })
  type: TaskType;

  // Mô tả chi tiết
  @Column({ type: 'text', nullable: true })
  description: string | null;

  // Trạng thái
  @Column({
    type: 'enum',
    enum: TaskStatus,
    default: TaskStatus.PENDING,
  })
  status: TaskStatus;

  // Thời gian dự kiến
  @Column({ type: 'timestamp', nullable: true })
  scheduled_at: Date | null;

  // Thời gian hoàn thành
  @Column({ type: 'timestamp', nullable: true })
  completed_at: Date | null;

  // Tự động tạo/cập nhật
  @CreateDateColumn()
  created_at: Date;

  @UpdateDateColumn()
  updated_at: Date;

  // Không cần @BeforeInsert() nữa vì dùng @CreateDateColumn
}

/**
 * ===================================================================
 * DTO: Tạo mới Service Task
 * ===================================================================
 */
export class CreateServiceTaskDto {
  @IsInt()
  @Min(1)
  vehicle_id: number;

  @IsOptional()
  @IsInt()
  @Min(1)
  assigned_to?: number;

  @IsEnum(TaskType, {
    message:
      'type must be one of: maintenance, inspection, charging, cleaning, other',
  })
  type?: TaskType;

  @IsOptional()
  @IsString()
  @MaxLength(1000)
  description?: string;

  @IsOptional()
  @IsEnum(TaskStatus)
  status?: TaskStatus;

  @IsOptional()
  @IsDate()
  scheduled_at?: Date;

  @IsOptional()
  @IsDate()
  completed_at?: Date;
}

/**
 * ===================================================================
 * DTO: Cập nhật Service Task
 * ===================================================================
 */
export class UpdateServiceTaskDto {
  @IsOptional()
  @IsInt()
  @Min(1)
  assigned_to?: number | null;

  @IsOptional()
  @IsEnum(TaskType)
  type?: TaskType;

  @IsOptional()
  @IsString()
  @MaxLength(1000)
  description?: string | null;

  @IsOptional()
  @IsEnum(TaskStatus)
  status?: TaskStatus;

  @IsOptional()
  @IsDate()
  scheduled_at?: Date | null;

  @IsOptional()
  @IsDate()
  completed_at?: Date | null;
}

/**
 * ===================================================================
 * DTO: Trả về cho client
 * ===================================================================
 */
export class ServiceTaskResponseDto {
  @IsInt()
  @Min(1)
  @Expose()
  task_id: number;

  @IsInt()
  @Min(1)
  @Expose()
  vehicle_id: number;

  @IsOptional()
  @IsInt()
  @Min(1)
  @Expose()
  assigned_to: number | null;

  @IsEnum(TaskType)
  @Expose()
  type: TaskType;

  @IsOptional()
  @IsString()
  @Expose()
  description: string | null;

  @IsEnum(TaskStatus)
  @Expose()
  status: TaskStatus;

  @IsOptional()
  @IsDate()
  @Expose()
  scheduled_at: Date | null;

  @IsOptional()
  @IsDate()
  @Expose()
  completed_at: Date | null;

  @IsDate()
  @Expose()
  created_at: Date;

  @IsDate()
  @Expose()
  updated_at: Date;
}
