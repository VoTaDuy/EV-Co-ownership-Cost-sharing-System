import { Controller, Get, Post, Delete, Body, Param } from '@nestjs/common';
import { UsageService } from './usage.service';
import { ApiTags, ApiOperation, ApiResponse, ApiParam, ApiBody, } from '@nestjs/swagger';
import { UsageRecord } from './usage.entity';
import { CreateUsageDto } from './dto/create-usage.dto';
import { UsageIdDto } from './dto/usage-id.dto';


@ApiTags('usage')
@Controller('usage')
export class UsageController {
  constructor(private readonly usageService: UsageService) {}

  @Post()
  @ApiOperation({ summary: 'Tạo bản ghi nhận sử dụng xe mới' })
  @ApiBody({ type: CreateUsageDto })
  @ApiResponse({
    status: 201,
    description: 'Tạo usage record thành công',
    type: UsageRecord,
  })
  async createUsage(@Body() data: CreateUsageDto) {
    const usageData: Partial<UsageRecord> = {
      ...data,
      checkin_time: data.checkin_time ? new Date(data.checkin_time) : undefined,
      checkout_time: data.checkout_time ? new Date(data.checkout_time) : undefined,
    };
    return this.usageService.createUsage(usageData);
  }

  @Get()
  @ApiOperation({ summary: 'Lấy danh sách tất cả các bản ghi sử dụng' })
  @ApiResponse({
    status: 200,
    description: 'Danh sách usage record',
    type: [UsageRecord],
  })
  async getAllUsage() {
    return this.usageService.getAllUsage();
  }

  @Get(':id')
    @ApiOperation({ summary: 'Lấy bản ghi cụ thể theo ID' })
    @ApiParam({ name: 'id', description: 'ID của usage record', example: 'usage-abc123' })
    @ApiResponse({
      status: 200,
      description: 'Thông tin usage record',
      type: UsageRecord,
    })
    @ApiResponse({ status: 404, description: 'Không tìm thấy usage record' })
    async getUsageById(@Param() params: UsageIdDto) {
      return this.usageService.getUsageById(params.usage_id);
    }

  @Delete(':id')
  @ApiOperation({ summary: 'Xóa bản ghi sử dụng xe theo ID' })
  @ApiParam({ name: 'id', description: 'ID của usage record', example: 'usage-xyz789' })
  @ApiResponse({ status: 200, description: 'Xóa thành công' })
  @ApiResponse({ status: 404, description: 'Không tìm thấy usage record' })
  async deleteUsage(@Param() params: UsageIdDto) {
    return this.usageService.deleteUsage(params.usage_id);
  }
}
