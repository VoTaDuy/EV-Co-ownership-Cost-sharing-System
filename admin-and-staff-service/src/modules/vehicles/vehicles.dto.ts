/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsString,
  IsOptional,
  IsBoolean,
  IsUUID,
  IsArray,
  IsNotEmpty,
  IsUrl,
} from 'class-validator';
import { PartialType } from '@nestjs/mapped-types';

/**
 * DTO dùng khi tạo mới xe
 */
export class CreateVehicleDto {
  @IsString()
  @IsNotEmpty()
  vehicle_name: string;

  @IsString()
  @IsNotEmpty()
  license_plate: string;

  @IsString()
  @IsOptional()
  description?: string;

  @IsUrl()
  @IsOptional()
  image_url?: string; // Ảnh đại diện của xe

  @IsArray()
  @IsOptional()
  spec_image_urls?: string[]; // Danh sách ảnh thông số kỹ thuật

  @IsBoolean()
  @IsOptional()
  is_active?: boolean;
}

/**
 * DTO dùng khi cập nhật thông tin xe
 */
export class UpdateVehicleDto extends PartialType(CreateVehicleDto) {}

/**
 * DTO trả về khi lấy thông tin xe
 */
export class VehicleResponseDto {
  @IsUUID()
  vehicle_id: string;

  @IsString()
  vehicle_name: string;

  @IsString()
  license_plate: string;

  @IsString()
  description: string;

  @IsUrl()
  image_url: string;

  @IsArray()
  spec_image_urls: string[];

  @IsBoolean()
  is_active: boolean;

  @IsString()
  created_at: Date;

  @IsString()
  updated_at: Date;
}
