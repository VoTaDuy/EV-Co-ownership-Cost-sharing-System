// src/dto/group-member.dto.ts
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
import { Expose, Type } from 'class-transformer';

/* Dùng chung type cho group_role để tránh lỗi TypeORM save() */
export type GroupRole = 'admin' | 'member';

/* ===================================================================
   1. TẠO THÀNH VIÊN MỚI (admin tạo thủ công)
   =================================================================== */
export class CreateGroupMemberDto {
  @IsInt({ message: 'group_id phải là số nguyên hợp lệ' })
  @Min(1, { message: 'group_id phải lớn hơn 0' })
  group_id: number;

  @IsInt({ message: 'user_id phải là số nguyên hợp lệ' })
  @Min(1, { message: 'user_id phải lớn hơn 0' })
  user_id: number;

  @IsOptional()
  @IsEnum(['admin', 'member'] as const, {
    message: 'group_role chỉ được là: admin hoặc member',
  })
  group_role?: GroupRole;

  @IsOptional()
  @IsNumber(
    { maxDecimalPlaces: 2 },
    { message: 'ownership_ratio phải có tối đa 2 chữ số thập phân' },
  )
  @Min(0, { message: 'Tỷ lệ sở hữu không được âm' })
  @Max(100, { message: 'Tỷ lệ sở hữu không được quá 100%' })
  ownership_ratio?: number;
}

/* ===================================================================
   2. THÊM THÀNH VIÊN VÀO NHÓM ĐÃ TỒN TẠI (group_id từ param)
   =================================================================== */
export class AddGroupMemberDto {
  @IsInt({ message: 'user_id phải là số nguyên hợp lệ' })
  @Min(1)
  user_id: number;

  @IsOptional()
  @IsEnum(['admin', 'member'] as const, {
    message: 'group_role chỉ được là: admin hoặc member',
  })
  group_role?: GroupRole;

  @IsOptional()
  @IsNumber({ maxDecimalPlaces: 2 })
  @Min(0)
  @Max(100)
  ownership_ratio?: number;
}

/* ===================================================================
   3. CẬP NHẬT THÀNH VIÊN (role + tỷ lệ)
   =================================================================== */
export class UpdateGroupMemberDto {
  @IsOptional()
  @IsEnum(['admin', 'member'] as const, {
    message: 'group_role chỉ được là: admin hoặc member',
  })
  group_role?: GroupRole;

  @IsOptional()
  @IsNumber(
    { maxDecimalPlaces: 2 },
    { message: 'ownership_ratio phải có tối đa 2 chữ số thập phân' },
  )
  @Min(0, { message: 'Tỷ lệ sở hữu không được âm' })
  @Max(100, { message: 'Tỷ lệ sở hữu không được quá 100%' })
  ownership_ratio?: number;
}

/* ===================================================================
   4. RESPONSE TRẢ VỀ CHO FRONTEND (populate user)
   =================================================================== */
export class GroupMemberResponseDto {
  @IsInt()
  @Expose()
  member_id: number;

  @IsInt()
  @Expose()
  group_id: number;

  @IsInt()
  @Expose()
  user_id: number;

  @IsString()
  @IsEnum(['admin', 'member'] as const)
  @Expose()
  group_role: GroupRole;

  @IsNumber()
  @Expose()
  ownership_ratio: number;

  @Type(() => Date)
  @IsDate()
  @Expose()
  created_at: Date;

  // Populate thông tin user (rất hay dùng ở frontend)
  @Expose()
  user?: {
    user_id: number;
    name: string;
    email: string;
    phone?: string | null;
    avatar?: string | null;
  };

  // Nếu cần trả thêm thông tin nhóm
  @Expose()
  group?: {
    group_id: number;
    group_name: string;
  };
}
