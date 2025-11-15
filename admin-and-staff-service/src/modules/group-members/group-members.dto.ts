/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsInt,
  Min,
  IsOptional,
  IsEnum,
  IsNumber,
  Max,
  IsDate,
  IsString,
} from 'class-validator';
import { Expose } from 'class-transformer';

/**
 * ===================================================================
 * CREATE GROUP MEMBER DTO
 * Dùng khi tạo thành viên thủ công (cần cả group_id và user_id)
 * ===================================================================
 */
export class CreateGroupMemberDto {
  @IsInt({ message: 'group_id must be a valid integer' })
  @Min(1, { message: 'group_id must be greater than 0' })
  group_id: number;

  @IsInt({ message: 'user_id must be a valid integer' })
  @Min(1, { message: 'user_id must be greater than 0' })
  user_id: number;

  @IsOptional()
  @IsEnum(['Owner', 'Co-owner', 'Viewer'], {
    message: 'group_role must be one of: Owner, Co-owner, Viewer',
  })
  group_role?: string;

  @IsOptional()
  @IsNumber({}, { message: 'ownership_ratio must be a number' })
  @Min(0, { message: 'ownership_ratio cannot be negative' })
  @Max(100, { message: 'ownership_ratio cannot exceed 100' })
  ownership_ratio?: number;
}

/**
 * ===================================================================
 * ADD GROUP MEMBER DTO
 * Dùng khi thêm user vào group có sẵn (group_id lấy từ param)
 * ===================================================================
 */
export class AddGroupMemberDto {
  @IsInt({ message: 'user_id must be a valid integer' })
  @Min(1, { message: 'user_id must be greater than 0' })
  user_id: number;

  @IsOptional()
  @IsEnum(['Owner', 'Co-owner', 'Viewer'], {
    message: 'group_role must be one of: Owner, Co-owner, Viewer',
  })
  group_role?: string;

  @IsOptional()
  @IsNumber({}, { message: 'ownership_ratio must be a number' })
  @Min(0)
  @Max(100)
  ownership_ratio?: number;
}

/**
 * ===================================================================
 * UPDATE GROUP MEMBER DTO
 * Dùng khi cập nhật role hoặc tỷ lệ sở hữu (theo member_id)
 * ===================================================================
 */
export class UpdateGroupMemberDto {
  @IsOptional()
  @IsEnum(['Owner', 'Co-owner', 'Viewer'], {
    message: 'group_role must be one of: Owner, Co-owner, Viewer',
  })
  group_role?: string;

  @IsOptional()
  @IsNumber({}, { message: 'ownership_ratio must be a number' })
  @Min(0)
  @Max(100)
  ownership_ratio?: number;
}

/**
 * ===================================================================
 * GROUP MEMBER RESPONSE DTO
 * Dùng khi trả về dữ liệu cho client
 * ===================================================================
 */
export class GroupMemberResponseDto {
  @IsInt()
  @Min(1)
  @Expose()
  member_id: number;

  @IsInt()
  @Min(1)
  @Expose()
  group_id: number;

  @IsInt()
  @Min(1)
  @Expose()
  user_id: number;

  @IsString()
  @Expose()
  group_role: string;

  @IsNumber()
  @Expose()
  ownership_ratio: number;

  @IsDate()
  @Expose()
  created_at: Date;
}
