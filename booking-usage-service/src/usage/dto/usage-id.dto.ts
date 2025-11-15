import { ApiProperty } from '@nestjs/swagger';
import { IsString } from 'class-validator';

export class UsageIdDto {
  @ApiProperty({
    description: 'ID của usage record (UUID)',
    example: '55',
  })
  @IsString()
  usage_id: number;
}
