import { IsNotEmpty, IsOptional, IsString } from 'class-validator';

export class CreateReportDto {
  @IsNotEmpty()
  @IsString()
  vehicle_id: string;

  @IsOptional()
  @IsString()
  template_id?: string;

  @IsOptional()
  @IsString()
  description?: string;

  @IsOptional()
  @IsString()
  status?: string;

  @IsOptional()
  @IsString()
  type?: string;
}

export class UpdateReportDto {
  @IsOptional()
  @IsString()
  template_id?: string;

  @IsOptional()
  @IsString()
  description?: string;

  @IsOptional()
  @IsString()
  status?: string;

  @IsOptional()
  @IsString()
  type?: string;
}
