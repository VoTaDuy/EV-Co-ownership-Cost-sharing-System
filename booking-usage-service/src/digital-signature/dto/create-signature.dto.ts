import { ApiProperty } from '@nestjs/swagger';
import { SignatureType } from '../digital-signature.entity';

export class CreateSignatureDto {
  @ApiProperty({
    description: 'ID của người ký (user)',
    example: '1',
  })
  user_id: number;

  @ApiProperty({
    description: 'ID của usage record liên quan',
    example: '1',
  })
  usage_id: number;

  @ApiProperty({
    description: 'Loại chữ ký: check-in hoặc check-out',
    enum: SignatureType,
    example: SignatureType.CHECKIN,
  })
  type: SignatureType;
}
