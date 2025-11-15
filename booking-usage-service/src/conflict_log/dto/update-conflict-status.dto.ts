import { ApiProperty } from '@nestjs/swagger';
import { ResolutionStatus } from '../conflict-log.entity';

export class UpdateConflictStatusDto {
  @ApiProperty({
    description: 'Trạng thái mới của conflict',
    enum: ResolutionStatus,
    example: ResolutionStatus.RESOLVED,
  })
  status: ResolutionStatus;

  @ApiProperty({
    description: 'ID của người thực hiện xử lý conflict (nếu có)',
    example: '678',
    required: false,
  })
  resolved_by?: number;
}
