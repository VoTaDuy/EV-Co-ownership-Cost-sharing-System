import { ApiProperty } from '@nestjs/swagger';

export class GetConflictByIdDto {
  @ApiProperty({
    description: 'ID của conflict cần lấy thông tin',
    example: '45',
  })
  conflict_id: number;
}
