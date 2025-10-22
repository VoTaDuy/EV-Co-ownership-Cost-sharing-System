import { ApiProperty } from '@nestjs/swagger';
import { IsString } from 'class-validator';

export class UsageIdDto {
  @ApiProperty({
    description: 'ID của usage record (UUID)',
    example: 'usage-550e8400-e29b-41d4-a716-446655440000',
  })
  @IsString()
  usage_id: string;
}
