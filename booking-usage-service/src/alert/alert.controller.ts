import { Controller, Get, Param, Post, Query } from '@nestjs/common';
import { AlertService } from './alert.service';
import { AlertLog } from './alert.entity';
import { AlertType } from './alert.entity';
import { ApiTags, ApiOperation, ApiQuery, ApiParam } from '@nestjs/swagger';

@ApiTags('Alerts')
@Controller('alerts')
export class AlertController {
  constructor(private readonly alertService: AlertService) {}

  // 1️⃣ Lấy tất cả alert (có thể lọc theo type)
  @Get()
  @ApiOperation({ summary: 'Lấy tất cả cảnh báo (có thể lọc theo loại)' })
  @ApiQuery({
    name: 'type',
    required: false,
    enum: AlertType,
    description: 'Lọc theo loại cảnh báo (LATE_CHECKIN hoặc LATE_CHECKOUT)',
  })
  async getAllAlerts(@Query('type') type?: AlertType): Promise<AlertLog[]> {
    return this.alertService.getAllAlerts(type);
  }

  // 2️⃣ Lấy alert theo user_id
  @Get('user/:user_id')
  @ApiOperation({ summary: 'Lấy tất cả cảnh báo của người dùng' })
  @ApiParam({ name: 'user_id', description: 'ID người dùng cần lấy cảnh báo' })
  async getAlertsByUser(@Param('user_id') user_id: string): Promise<AlertLog[]> {
    return this.alertService.getAlertsByUser(user_id);
  }

  // 3️⃣ Đánh dấu alert là "đã đọc"
  @Post(':id/read')
  @ApiOperation({ summary: 'Đánh dấu cảnh báo là đã đọc' })
  @ApiParam({ name: 'id', description: 'ID của alert cần cập nhật' })
  async markAsRead(@Param('id') id: string) {
    await this.alertService.markAlertAsRead(id);
    return { message: '✅ Alert marked as read' };
  }
}
