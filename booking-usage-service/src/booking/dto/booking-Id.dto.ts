import { ApiProperty } from '@nestjs/swagger';
import { IsUUID } from 'class-validator';

export class BookingIdDto {
  @ApiProperty({ example: 'a12b3c4d-5678-90ef-gh12-3456789ijklm' })
  @IsUUID()
  id: string;
}
