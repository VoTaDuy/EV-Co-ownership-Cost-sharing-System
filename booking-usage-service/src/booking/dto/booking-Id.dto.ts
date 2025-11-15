import { ApiProperty } from '@nestjs/swagger';
import { IsUUID } from 'class-validator';

export class BookingIdDto {
  @ApiProperty({ example: '1234' })
  id: number;
}
