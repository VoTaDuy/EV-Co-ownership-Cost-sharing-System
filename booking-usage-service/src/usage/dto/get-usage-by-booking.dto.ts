import { ApiProperty } from '@nestjs/swagger';
import { IsString } from 'class-validator';

export class GetUsageByBookingDto {
  @ApiProperty({
    description: 'ID của booking cần lấy usage record tương ứng',
    example: '345',
  })
  @IsString()
  booking_id: number;
}
