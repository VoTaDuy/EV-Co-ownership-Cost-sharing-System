import {
  IsUUID,
  IsOptional,
  IsNumber,
  IsEnum,
  Max,
  Min,
} from 'class-validator';

/**
 * Dto tạo mới thành viên group (dùng khi tạo thủ công)
 */
export class CreateGroupMemberDto {
  @IsUUID()
  group_id: string;

  @IsUUID()
  user_id: string;

  @IsOptional()
  @IsEnum(['Owner', 'Co-owner', 'Viewer'], {
    message: 'group_role must be one of: Owner, Co-owner, Viewer',
  })
  group_role?: string;

  @IsOptional()
  @IsNumber()
  ownership_ratio?: number;
}

/**
 * Dto cập nhật thông tin thành viên (vai trò hoặc tỷ lệ sở hữu)
 */
export class UpdateGroupMemberDto {
  @IsOptional()
  @IsEnum(['Owner', 'Co-owner', 'Viewer'])
  group_role?: string;

  @IsOptional()
  @IsNumber()
  ownership_ratio?: number;
}

/**
 * Dto thêm thành viên vào group có sẵn (từ danh sách user)
 * Giống CreateGroupMemberDto nhưng rõ mục đích hơn
 */
export class AddGroupMemberDto {
  @IsUUID()
  user_id: string;

  @IsOptional()
  @IsEnum(['Owner', 'Co-owner', 'Viewer'])
  group_role?: string;

  @IsOptional()
  @IsNumber()
  @Min(0)
  @Max(100)
  ownership_ratio?: number;
}
