import { ApiProperty } from '@nestjs/swagger';

export class IdSignatureDto {
  @ApiProperty({
    description: 'ID của chữ ký số cần lấy',
    example: '13',
  })
  id: number;
}
