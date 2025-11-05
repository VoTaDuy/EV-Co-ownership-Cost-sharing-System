import { ApiProperty } from '@nestjs/swagger';

export class GetConflictsByBookingDto {
  @ApiProperty({
    description: 'ID của booking cần xem danh sách conflict',
    example: 'bkg-12345678-abcd-efgh-ijkl-9876543210',
  })
  booking_id: string;
}
