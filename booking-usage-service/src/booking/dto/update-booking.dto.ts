import { ApiProperty } from '@nestjs/swagger';
import { IsEnum, IsOptional, IsString, IsDateString } from 'class-validator';
import { BookingStatus } from '../booking.entity';

export class UpdateBookingDto {

  @ApiProperty({ required: false })
  @IsOptional()
  @IsDateString()
  booking_date?: Date;

  @ApiProperty({ example: '08:00', required: false })
  @IsOptional()
  @IsString()
  check_in_time?: string;

  @ApiProperty({ example: '10:00', required: false })
  @IsOptional()
  @IsString()
  check_out_time?: string;

  @ApiProperty({ enum: BookingStatus, required: false })
  @IsOptional()
  @IsEnum(BookingStatus)
  booking_status?: BookingStatus;

  @ApiProperty({ example: 'Người dùng hủy do thay đổi kế hoạch', required: false })
  @IsOptional()
  @IsString()
  cancel_reason?: string;
}
