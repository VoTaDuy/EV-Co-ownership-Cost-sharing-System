/* eslint-disable @typescript-eslint/no-unsafe-call */
import { IsNotEmpty, IsString, IsInt, Min, IsDate } from 'class-validator';
import { PartialType } from '@nestjs/mapped-types';

export class CreateOwnershipGroupDto {
  @IsString()
  @IsNotEmpty()
  group_name: string;

  @IsInt()
  @Min(1)
  @IsNotEmpty()
  vehicle_id: number;

  @IsInt()
  @IsNotEmpty()
  created_by: number; // hoặc đổi thành number nếu user_id là int
}

export class UpdateOwnershipGroupDto extends PartialType(
  CreateOwnershipGroupDto,
) {
  // Không bao gồm group_id vì là auto-increment, không được update
}

export class OwnershipGroupResponseDto {
  @IsInt()
  @Min(1)
  group_id: number;

  @IsString()
  group_name: string;

  @IsInt()
  @Min(1)
  vehicle_id: number;

  @IsInt()
  created_by: number;

  @IsDate()
  created_at: Date;

  @IsDate()
  updated_at: Date;
}
