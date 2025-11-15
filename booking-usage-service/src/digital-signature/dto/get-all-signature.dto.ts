// DTO này chỉ để định nghĩa cấu trúc response (nếu cần dùng swagger model)
import { ApiProperty } from '@nestjs/swagger';

export class GetAllSignatureDto {
  @ApiProperty({ description: 'ID của chữ ký số' })
  signature_id: number;

  @ApiProperty({ description: 'ID người dùng' })
  user_id: number;

  @ApiProperty({ description: 'ID bản ghi sử dụng (usage)' })
  usage_id: number

  @ApiProperty({ description: 'Loại chữ ký (checkin/checkout)' })
  type: string;

  @ApiProperty({ description: 'Dữ liệu hash chữ ký số' })
  signature_data: string;

  @ApiProperty({ description: 'Thời gian ký' })
  signed_at: Date;
}
