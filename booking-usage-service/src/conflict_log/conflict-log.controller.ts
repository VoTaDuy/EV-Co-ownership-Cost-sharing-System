import { Controller, Get, Post, Param, Body, Patch } from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { ConflictLogService } from './conflict-log.service';
import { ResolutionStatus } from './conflict-log.entity';
import { UpdateConflictStatusDto } from './dto/update-conflict-status.dto';
import { GetConflictByIdDto } from './dto/get-conflict-by-id.dto'; 
import { GetConflictsByBookingDto } from './dto/get-conflict-by-booking.dto';

@ApiTags('conflict-log')
@Controller('conflict-log')
export class ConflictLogController {
  constructor(private readonly conflictService: ConflictLogService) {}

  @Post()
  @ApiOperation({ summary: 'Tạo một conflict log mới (thường do hệ thống tự động tạo)' })
  async createConflict(@Body() body: { booking_id: string; description: string }) {
    return this.conflictService.createConflict(body.booking_id, body.description);
  }

@Get()
  @ApiOperation({ summary: 'Lấy tất cả conflict logs' })
  async getAll() {
    return this.conflictService.getAllConflicts();
  }

  @Get(':id')
  @ApiOperation({ summary: 'Lấy conflict log theo ID' })
  async getById(@Param() params: GetConflictByIdDto) {
    return this.conflictService.getConflictById(params.conflict_id);
  }

  @Get('booking/:booking_id')
  @ApiOperation({ summary: 'Lấy danh sách conflict theo booking_id' })
  async getByBooking(@Param() params: GetConflictsByBookingDto) {
    return this.conflictService.getConflictsByBooking(params.booking_id);
  }

  @Patch(':id/status')
  @ApiOperation({ summary: 'Cập nhật trạng thái conflict (resolve, reject, ...)' })
  async updateStatus(@Param('id') conflict_id: string, @Body() body: UpdateConflictStatusDto) {
    return this.conflictService.updateConflictStatus(conflict_id, body.status, body.resolved_by);
  }
}
