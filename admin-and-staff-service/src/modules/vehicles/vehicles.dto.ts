/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsString,
  IsOptional,
  IsBoolean,
  IsArray,
  IsNotEmpty,
  IsUrl,
  IsDateString,
  IsInt,
} from 'class-validator';
import { PartialType } from '@nestjs/mapped-types';

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
  image_url?: string;

  @IsArray()
  @IsOptional()
  @IsUrl(
    {},
    {
      each: true,
      message: 'Mỗi phần tử trong spec_image_urls phải là URL hợp lệ',
    },
  )
  spec_image_urls?: string[];

  @IsBoolean()
  @IsOptional()
  is_active?: boolean = true;
}

export class UpdateVehicleDto extends PartialType(CreateVehicleDto) {}

export class VehicleResponseDto {
  @IsInt()
  vehicle_id: number;

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
