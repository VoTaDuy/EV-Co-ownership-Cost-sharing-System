import { ApiProperty } from '@nestjs/swagger';

export class IdSignatureDto {
  @ApiProperty({
    description: 'ID của chữ ký số cần lấy',
    example: 'sig-1234abcd',
  })
  id: string;
}
