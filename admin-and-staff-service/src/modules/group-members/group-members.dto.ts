import {
  IsString,
  IsOptional,
  IsUUID,
  IsNumber,
  Min,
  Max,
} from 'class-validator';

export class CreateGroupMemberDto {
  @IsUUID() // kiểm tra member_id phải là UUID hợp lệ
  member_id: string;

  @IsUUID() // kiểm tra group_id phải là UUID hợp lệ
  group_id: string;

  @IsOptional() // không bắt buộc
  @IsString({ message: 'group_role phải là chuỗi' })
  group_role?: string;

  @IsOptional()
  @IsNumber({}, { message: 'ownership_ratio phải là số' })
  @Min(0, { message: 'ownership_ratio phải >= 0' })
  @Max(100, { message: 'ownership_ratio phải <= 100' })
  ownership_ratio?: number;
}

export class UpdateGroupMemberDto {
  @IsOptional()
  @IsString({ message: 'group_role phải là chuỗi' })
  group_role?: string;

  @IsOptional()
  @IsNumber({}, { message: 'ownership_ratio phải là số' })
  @Min(0, { message: 'ownership_ratio phải >= 0' })
  @Max(100, { message: 'ownership_ratio phải <= 100' })
  ownership_ratio?: number;
}
