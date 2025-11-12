import { ApiProperty } from '@nestjs/swagger';

export class GetConflictsByUserDto {
  @ApiProperty({
    description: 'ID của người dùng cần xem danh sách conflict',
    example: 'user-12345',
  })
  user_id: string;
}
