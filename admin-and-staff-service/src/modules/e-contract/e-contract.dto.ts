/* eslint-disable @typescript-eslint/no-unsafe-call */
import {
  IsInt,
  Min,
  IsUrl,
  IsEnum,
  IsOptional,
  IsDate,
  IsString,
} from 'class-validator';
import { SignatureStatus } from './e-contract.entity';

/**
 * ===================================================================
 * CREATE E-CONTRACT DTO
 * Dùng khi tạo mới hợp đồng điện tử
 * ===================================================================
 */
export class CreateEContractDto {
  @IsInt({ message: 'ownership_group_id must be a valid integer' })
  @Min(1, { message: 'ownership_group_id must be greater than 0' })
  ownership_group_id: number;

  @IsOptional()
  @IsUrl({}, { message: 'contract_url must be a valid URL' })
  contract_url?: string;

  @IsInt({ message: 'user_id must be a valid integer' })
  @Min(1, { message: 'user_id must be greater than 0' })
  user_id: number;

  @IsOptional()
  @IsEnum(SignatureStatus, {
    message:
      'signature_status must be one of: pending, signed, rejected, expired',
  })
  signature_status?: SignatureStatus;
}

/**
 * ===================================================================
 * UPDATE E-CONTRACT DTO
 * Dùng khi cập nhật URL, trạng thái hoặc thời gian ký
 * ===================================================================
 */
export class UpdateEContractDto {
  @IsOptional()
  @IsUrl({}, { message: 'contract_url must be a valid URL' })
  contract_url?: string;

  @IsOptional()
  @IsEnum(SignatureStatus, {
    message:
      'signature_status must be one of: pending, signed, rejected, expired',
  })
  signature_status?: SignatureStatus;

  @IsOptional()
  @IsDate({ message: 'signed_at must be a valid date' })
  signed_at?: Date | null;
}

/**
 * ===================================================================
 * RESPONSE DTO (gợi ý – dùng khi trả về API)
 * ===================================================================
 */
export class EContractResponseDto {
  @IsInt()
  @Min(1)
  contract_id: number;

  @IsInt()
  @Min(1)
  ownership_group_id: number;

  @IsInt()
  @Min(1)
  user_id: number;

  @IsString()
  @IsUrl()
  contract_url: string;

  @IsEnum(SignatureStatus)
  signature_status: SignatureStatus;

  @IsOptional()
  @IsDate()
  signed_at: Date | null;

  @IsDate()
  created_at: Date;

  @IsDate()
  updated_at: Date;
}
