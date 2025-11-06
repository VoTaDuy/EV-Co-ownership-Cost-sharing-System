import { ApiProperty } from '@nestjs/swagger';

export class UserSignatureDto {
  @ApiProperty({
    description: 'ID của người dùng cần lấy chữ ký',
    example: 'user-1234',
  })
  user_id: string;
}
