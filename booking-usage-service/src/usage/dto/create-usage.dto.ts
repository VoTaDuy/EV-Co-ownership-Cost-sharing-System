import { ApiProperty } from '@nestjs/swagger';
import { IsString, IsOptional, IsISO8601, IsNumber } from 'class-validator';

export class CreateUsageDto {
  @ApiProperty({
    description: 'ID của booking liên quan',
    example: 'bkg-550e8400-e29b-41d4-a716-446655440000',
  })
  @IsString()
  booking_id: string;

  @ApiProperty({
    description: 'ID của người dùng (co-owner) thực hiện checkin/checkout',
    example: 'user-1234',
  })
  @IsString()
  user_id: string;

  @ApiProperty({
    description: 'Thời điểm check-in (ISO 8601). Có thể để trống nếu chưa checkin.',
    example: '2025-10-20T08:30:00Z',
    required: false,
  })
  @IsOptional()
  @IsISO8601()
  checkin_time?: string;

  @ApiProperty({
    description: 'Thời điểm check-out (ISO 8601). Có thể để trống nếu chưa checkout.',
    example: '2025-10-20T10:30:00Z',
    required: false,
  })
  @IsOptional()
  @IsISO8601()
  checkout_time?: string;

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
