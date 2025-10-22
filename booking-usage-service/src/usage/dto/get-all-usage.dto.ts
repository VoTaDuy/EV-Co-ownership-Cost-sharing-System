import { ApiPropertyOptional } from '@nestjs/swagger';
import { IsOptional, IsString, IsInt, Min } from 'class-validator';
import { Type } from 'class-transformer';

export class GetAllUsageDto {
  @ApiPropertyOptional({ description: 'Lọc theo user_id', example: 'user-1234' })
  @IsOptional()
  @IsString()
  user_id?: string;

  @ApiPropertyOptional({ description: 'Lọc theo booking_id', example: 'bkg-550e...' })
  @IsOptional()
  @IsString()
  booking_id?: string;

  @ApiPropertyOptional({ description: 'Số trang (pagination)', example: 1 })
  @IsOptional()
  @Type(() => Number)
  @IsInt()
  @Min(1)
  page?: number;

  @ApiPropertyOptional({ description: 'Kích thước trang', example: 20 })
  @IsOptional()
  @Type(() => Number)
  @IsInt()
  @Min(1)
  limit?: number;
}
