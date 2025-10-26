/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsString,
  IsOptional,
  IsBoolean,
  IsUUID,
  IsArray,
  IsNotEmpty,
  IsUrl,
  IsDateString,
} from 'class-validator';
import { PartialType } from '@nestjs/mapped-types';

/**
 * DTO dùng khi tạo mới xe
 */
export class CreateVehicleDto {
  @IsString()
  @IsNotEmpty({ message: 'Tên xe không được để trống' })
  vehicle_name: string;

  @IsString()
  @IsNotEmpty({ message: 'Biển số xe không được để trống' })
  license_plate: string;

  @IsString()
  @IsOptional()
  description?: string;

  @IsUrl({}, { message: 'image_url phải là một URL hợp lệ' })
  @IsOptional()
  image_url?: string; // Ảnh đại diện của xe

  @IsArray()
  @IsOptional()
  @IsUrl(
    {},
    {
      each: true,
      message: 'Mỗi phần tử trong spec_image_urls phải là URL hợp lệ',
    },
  )
  spec_image_urls?: string[]; // Danh sách ảnh thông số kỹ thuật

  @IsBoolean()
  @IsOptional()
  is_active?: boolean = true;
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
  @IsOptional()
  description?: string;

  @IsUrl()
  @IsOptional()
  image_url?: string;

  @IsArray()
  @IsOptional()
  spec_image_urls?: string[];

  @IsBoolean()
  is_active: boolean;

  @IsDateString()
  created_at: Date;

  @IsDateString()
  updated_at: Date;
}
