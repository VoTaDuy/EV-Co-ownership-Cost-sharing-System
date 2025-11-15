import { ApiProperty } from '@nestjs/swagger';

export class CreateBookingDto {
  @ApiProperty({
    description: 'ID của chủ xe (Co-owner)',
    example: '14',
  })
  user_id: number;

  @ApiProperty({
    description: 'ID của xe được đặt',
    example: '78',
  })
  vehicle_id: number;

  @ApiProperty({
    description: 'Ngày bắt đầu sử dụng xe',
    example: '2025-12-15',
  })
  start_date: Date;

  @ApiProperty({
    description: 'Ngày kết thúc sử dụng xe',
    example: '2025-12-16',
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
