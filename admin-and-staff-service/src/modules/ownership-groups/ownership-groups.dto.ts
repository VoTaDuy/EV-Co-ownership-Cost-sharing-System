/* eslint-disable @typescript-eslint/no-unsafe-call */
import { IsNotEmpty, IsUUID, IsString, IsDate } from 'class-validator';
import { PartialType } from '@nestjs/mapped-types';

export class CreateOwnershipGroupDto {
  @IsString()
  @IsNotEmpty()
  group_name: string;

  @IsUUID()
  @IsNotEmpty()
  vehicle_id: string;

  @IsString()
  @IsNotEmpty()
  created_by: string;
}

export class UpdateOwnershipGroupDto extends PartialType(
  CreateOwnershipGroupDto,
) {}

export class OwnershipGroupResponseDto {
  @IsUUID()
  group_id: string;

  @IsString()
  group_name: string;

  @IsUUID()
  vehicle_id: string;

  @IsString()
  created_by: string;

  @IsDate()
  created_at: Date;

  @IsDate()
  updated_at: Date;
}
