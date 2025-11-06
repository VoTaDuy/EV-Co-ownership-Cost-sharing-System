import { ApiProperty } from '@nestjs/swagger';
import { SignatureType } from '../digital-signature.entity';

export class CreateSignatureDto {
  @ApiProperty({
    description: 'ID của người ký (user)',
    example: 'user-1234',
  })
  user_id: string;

  @ApiProperty({
    description: 'ID của usage record liên quan',
    example: 'usage-5678',
  })
  usage_id: string;

  @ApiProperty({
    description: 'Loại chữ ký: check-in hoặc check-out',
    enum: SignatureType,
    example: SignatureType.CHECKIN,
  })
  type: SignatureType;
}
