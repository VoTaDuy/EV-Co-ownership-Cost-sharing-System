/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsEnum,
  IsOptional,
  IsString,
  IsUUID,
  IsDateString,
  IsNotEmpty,
} from 'class-validator';
import { PartialType } from '@nestjs/mapped-types';

export class CreateServiceTaskDto {
  @IsUUID()
  @IsNotEmpty()
  vehicle_id: string;

  @IsString()
  @IsOptional()
  assigned_to?: string;

  @IsEnum(['maintenance', 'inspection', 'charging', 'cleaning', 'other'])
  @IsNotEmpty()
  type: 'maintenance' | 'inspection' | 'charging' | 'cleaning' | 'other';

  @IsString()
  @IsOptional()
  description?: string;

  @IsEnum(['pending', 'in_progress', 'completed', 'cancelled'])
  @IsOptional()
  status?: 'pending' | 'in_progress' | 'completed' | 'cancelled';

  @IsDateString()
  @IsOptional()
  scheduled_at?: Date;

  @IsDateString()
  @IsOptional()
  completed_at?: Date;
}

export class UpdateServiceTaskDto extends PartialType(CreateServiceTaskDto) {}

export class ServiceTaskResponseDto {
  @IsUUID()
  task_id: string;

  @IsUUID()
  vehicle_id: string;

  @IsString()
  assigned_to: string;

  @IsEnum(['maintenance', 'inspection', 'charging', 'cleaning', 'other'])
  type: string;

  @IsString()
  description: string;

  @IsEnum(['pending', 'in_progress', 'completed', 'cancelled'])
  status: string;

  @IsDateString()
  scheduled_at: Date;

  @IsDateString()
  completed_at: Date;

  @IsDateString()
  created_at: Date;

  @IsDateString()
  updated_at: Date;
}
