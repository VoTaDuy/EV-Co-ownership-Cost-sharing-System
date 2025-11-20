import { Controller, Get, Param, Post, Query } from '@nestjs/common';
import { AlertService } from './alert.service';
import { AlertLog } from './alert.entity';
import { AlertType } from './alert.entity';
import { ApiTags, ApiOperation, ApiQuery, ApiParam } from '@nestjs/swagger';

@ApiTags('Alerts')
@Controller('booking/alerts')
export class AlertController {
  constructor(private readonly alertService: AlertService) {}

  // Lấy tất cả alert (có thể lọc theo type)
  @Get('get-all')
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

  // Lấy alert theo user_id 
  @Get('user/:user_id')
  @ApiOperation({ summary: 'Lấy tất cả cảnh báo của người dùng' })
  @ApiParam({ name: 'user_id', description: 'ID người dùng cần lấy cảnh báo' })
  async getAlertsByUser(@Param('user_id') user_id: number): Promise<AlertLog[]> {
    return this.alertService.getAlertsByUser(user_id);
  }
}
