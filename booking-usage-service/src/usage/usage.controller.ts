import { Controller, Get, Post, Delete, Body, Put, Param } from '@nestjs/common';
import { UsageService } from './usage.service';
import { ApiTags, ApiOperation, ApiResponse, ApiParam, ApiBody, } from '@nestjs/swagger';
import { UsageRecord } from './usage.entity';
import { CreateUsageDto } from './dto/create-usage.dto';
import { UsageIdDto } from './dto/usage-id.dto';
import { UpdateUsageDto } from './dto/update-usage.dto';


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
    @ApiResponse({
      status: 200,
      description: 'Thông tin usage record',
      type: UsageRecord,
    })
    @ApiResponse({ status: 404, description: 'Không tìm thấy usage record' })
    async getUsageById(@Param() params: UsageIdDto) {
      return this.usageService.getUsageById(params.usage_id);
    }

  @Put(':id') 
  @ApiOperation({ summary: 'Cập nhật thông tin bản ghi usage' })
  @ApiBody({ type: UpdateUsageDto })
  @ApiResponse({
    status: 200,
    description: 'Cập nhật usage record thành công',
    type: UsageRecord,
  })
  @ApiResponse({ status: 404, description: 'Không tìm thấy usage record' })
  async updateUsage(
    @Param() params: UsageIdDto,
    @Body() updateDto: UpdateUsageDto,
  ) {
    const data: Partial<UsageRecord> = {
      ...(updateDto as unknown as Partial<UsageRecord>),
      start_date: updateDto.start_date ? new Date(updateDto.start_date) : undefined,
      end_date: updateDto.end_date ? new Date(updateDto.end_date) : undefined,
    };
    return this.usageService.updateUsage(params.usage_id, data);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Xóa bản ghi sử dụng xe theo ID' })
  @ApiResponse({ status: 200, description: 'Xóa thành công' })
  @ApiResponse({ status: 404, description: 'Không tìm thấy usage record' })
  async deleteUsage(@Param() params: UsageIdDto) {
    return this.usageService.deleteUsage(params.usage_id);
  }
}
