import { PartialType } from '@nestjs/swagger';
import { CreateUsageDto } from './create-usage.dto';
import { ApiProperty } from '@nestjs/swagger';
import { IsOptional, IsString, IsNumber, IsISO8601 } from 'class-validator';

export class UpdateUsageDto extends PartialType(CreateUsageDto) {
  @ApiProperty({
    description: 'Cập nhật thời điểm check-in (ISO 8601)',
    example: '09:00',
    required: false,
  })
  @IsOptional()
  @IsISO8601()
  check_in_time?: string;

  @ApiProperty({
    description: 'Cập nhật thời điểm check-out (ISO 8601)',
    example: '17:00',
    required: false,
  })
  @IsOptional()
  @IsISO8601()
  check_out_time?: string;

  @ApiProperty({
    description: 'Cập nhật tình trạng xe khi trả',
    example: 'Có trầy nhẹ ở cản trước',
    required: false,
  })
  @IsOptional()
  @IsString()
  vehicle_condition?: string;

  @ApiProperty({
    description: 'Cập nhật quãng đường đã đi (km)',
    example: 15.2,
    required: false,
  })
  @IsOptional()
  @IsNumber()
  distance?: number;
}
