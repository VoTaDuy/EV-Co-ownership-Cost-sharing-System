import { ApiProperty } from '@nestjs/swagger';

export class CreateBookingDto {
  @ApiProperty({
    description: 'ID của chủ xe (Co-owner)',
    example: 'user-1234',
  })
  user_id: string;

  @ApiProperty({
    description: 'ID của xe được đặt',
    example: 'vehicle-5678',
  })
  vehicle_id: string;

  @ApiProperty({
    description: 'Ngày bắt đầu sử dụng xe',
    example: '2024-07-15',
  })
  start_date: Date;

  @ApiProperty({
    description: 'Ngày kết thúc sử dụng xe',
    example: '2024-07-15',
  })
  end_date: Date;

  @ApiProperty({
    description: 'Thời gian check-in khi nhận xe (định dạng HH:MM)',
    example: '09:00',
  })
  check_in_time: string;

  @ApiProperty({
    description: 'Thời gian check-out khi trả xe (định dạng HH:MM)',
    example: '17:00',
  })
  check_out_time: string;
}
