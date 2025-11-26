import { Controller, Get, Post, Param, Body, Patch, Query } from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { ConflictLogService } from './conflict-log.service';
import { ResolutionStatus } from './conflict-log.entity';
import { UpdateConflictStatusDto } from './dto/update-conflict-status.dto';
import { GetConflictByIdDto } from './dto/get-conflict-by-id.dto'; 
import { GetConflictsByUserDto } from './dto/get-conflict-by-user.dto';

@ApiTags('conflict-log')
@Controller('booking/conflict-log')
export class ConflictLogController {
  constructor(private readonly conflictService: ConflictLogService) {}

  @Post()
  @ApiOperation({ summary: 'Tạo một conflict log mới (thường do hệ thống tự động tạo)' })
  async createConflict(@Body() body: { user_id: number, booking_id: number; description: string }) {
    return this.conflictService.createConflict(body.user_id, body.booking_id, body.description);
  }

  @Get()
  @ApiOperation({ summary: 'Lấy tất cả conflict logs' })
  async getAll() {
    return this.conflictService.getAllConflicts();
  }

  @Get('stats/total')
  async getTotalConflicts() {
    return {
      total: await this.conflictService.getTotalConflicts(),
    };
  }

  @Get('stats/by-month')
  async getConflictsByMonth(@Query('year') year: string) {
    return {
      year: Number(year),
      data: await this.conflictService.getConflictsByMonth(Number(year)),
    };
  }

  @Get(':conflict_id')
  @ApiOperation({ summary: 'Lấy conflict log theo ID' })
  async getById(@Param() params: GetConflictByIdDto) {
    return this.conflictService.getConflictById(params.conflict_id);
  }

  @Get('user_id/:user_id')
  @ApiOperation({ summary: 'Lấy danh sách conflict theo user ID' })
  async getByBooking(@Param() params: GetConflictsByUserDto) {
    return this.conflictService.getConflictsByUser(Number(params.user_id));
  }

  @Patch(':id/status')
  @ApiOperation({ summary: 'Cập nhật trạng thái conflict (resolve, reject, ...)' })
  async updateStatus(@Param('id') conflict_id: number, @Body() body: UpdateConflictStatusDto) {
    return this.conflictService.updateConflictStatus(conflict_id, body.status, body.resolved_by);
  }
}
