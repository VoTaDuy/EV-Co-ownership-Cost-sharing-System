/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsInt,
  Min,
  IsOptional,
  IsEnum,
  IsString,
  IsDate,
  MaxLength,
  IsNotEmpty,
} from 'class-validator';
import { Expose } from 'class-transformer';

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
 * DTO: Tạo mới Service Task
 * ===================================================================
 */
export class CreateServiceTaskDto {
  /** ID xe (FK → vehicles.id) */
  @IsInt({ message: 'vehicle_id must be a valid integer' })
  @Min(1, { message: 'vehicle_id must be greater than 0' })
  @IsNotEmpty()
  vehicle_id: number;

  /** ID người được giao (FK → users.id) – nullable */
  @IsOptional()
  @IsInt({ message: 'assigned_to must be a valid integer' })
  @Min(1)
  assigned_to?: number;

  /** Loại công việc */
  @IsEnum(TaskType, {
    message:
      'type must be one of: maintenance, inspection, charging, cleaning, other',
  })
  @IsNotEmpty()
  type: TaskType;

  /** Mô tả chi tiết */
  @IsOptional()
  @IsString()
  @MaxLength(1000, { message: 'description cannot exceed 1000 characters' })
  description?: string;

  /** Trạng thái (mặc định: pending) */
  @IsOptional()
  @IsEnum(TaskStatus, {
    message:
      'status must be one of: pending, in_progress, completed, cancelled',
  })
  status?: TaskStatus;

  /** Thời gian dự kiến */
  @IsOptional()
  @IsDate({ message: 'scheduled_at must be a valid date' })
  scheduled_at?: Date;

  /** Thời gian hoàn thành */
  @IsOptional()
  @IsDate({ message: 'completed_at must be a valid date' })
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
 * DTO: Trả về cho client (Response)
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
