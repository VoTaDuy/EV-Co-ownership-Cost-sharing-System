import { ApiProperty } from '@nestjs/swagger';
import {
  IsString,
  IsOptional,
  IsISO8601,
  IsNumber,
  IsUUID,
} from 'class-validator';

export class CreateUsageDto {
  @ApiProperty({
    description: 'ID của booking liên quan',
    example: 'ed0c0207-9159-43f0-915d-9c8e1873afc8',
  })
  @IsUUID()
  booking_id: string;

  @ApiProperty({
    description: 'ID của người dùng (co-owner) thực hiện checkin/checkout',
    example: 'user-1234',
  })
  @IsString()
  user_id: string;

  @ApiProperty({
    description: 'ID của xe được sử dụng',
    example: 'vehicle-5678',
  })
  @IsString()
  vehicle_id: string;

  @ApiProperty({
    description: 'Ngày bắt đầu sử dụng xe (ISO 8601)',
    example: '2025-11-15',
  })
  @IsISO8601()
  start_date: string;

  @ApiProperty({
    description: 'Ngày kết thúc sử dụng xe (ISO 8601)',
    example: '2025-11-16',
  })
  @IsISO8601()
  end_date: string;

  @ApiProperty({
    description: 'Thời điểm check-in (ISO 8601). Có thể để trống nếu chưa check-in.',
    example: '09:00',
    required: false,
  })
  @IsOptional()
  @IsISO8601()
  check_in_time?: string;

  @ApiProperty({
    description: 'Thời điểm check-out (ISO 8601). Có thể để trống nếu chưa check-out.',
    example: '17:00',
    required: false,
  })
  @IsOptional()
  @IsISO8601()
  check_out_time?: string;

  @ApiProperty({
    description: 'Mô tả tình trạng xe tại thời điểm trả (nếu có)',
    example: 'Không trầy xước, pin 80%',
    required: false,
  })
  @IsOptional()
  @IsString()
  vehicle_condition?: string;

  @ApiProperty({
    description: 'Quãng đường đã đi (km). Có thể bỏ trống nếu chưa có dữ liệu.',
    example: 12.5,
    required: false,
  })
  @IsOptional()
  @IsNumber()
  distance?: number;
}
