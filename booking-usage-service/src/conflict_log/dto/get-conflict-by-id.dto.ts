import { ApiProperty } from '@nestjs/swagger';

export class GetConflictByIdDto {
  @ApiProperty({
    description: 'ID của conflict cần lấy thông tin',
    example: 'conf-abcdef12-3456-7890-ghij-klmnopqrst',
  })
  conflict_id: string;
}
