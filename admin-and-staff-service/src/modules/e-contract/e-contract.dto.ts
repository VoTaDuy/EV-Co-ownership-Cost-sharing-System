import { IsUUID, IsOptional, IsString, IsDate } from 'class-validator';

export class CreateEContactDto {
  @IsUUID()
  member_id: string;

  @IsUUID()
  vehicle_id: string;

  @IsUUID()
  template_id: string;

  @IsOptional()
  @IsString({ message: 'signature_status phải là chuỗi' })
  signature_status?: string;

  @IsOptional()
  @IsDate({ message: 'signed_at phải là kiểu Date' })
  signed_at?: Date;
}

export class UpdateEContactDto {
  @IsOptional()
  @IsString({ message: 'signature_status phải là chuỗi' })
  signature_status?: string;

  @IsOptional()
  @IsDate({ message: 'signed_at phải là kiểu Date' })
  signed_at?: Date;
}
