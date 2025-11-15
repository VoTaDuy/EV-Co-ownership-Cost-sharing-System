import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { AlertLog, AlertType } from './alert.entity';

@Injectable()
export class AlertService {
  constructor(
    @InjectRepository(AlertLog)
    private readonly alertRepo: Repository<AlertLog>,
  ) {}

  // Lấy tất cả alert, có thể lọc theo loại
  async getAllAlerts(type?: AlertType): Promise<AlertLog[]> {
    const where = type ? { alert_type: type } : {};
    return this.alertRepo.find({
      where,
      order: { created_at: 'DESC' },
    });
  }

  // Lấy alert theo user_id
  async getAlertsByUser(user_id: number): Promise<AlertLog[]> {
    return this.alertRepo.find({
      where: { user_id },
      order: { created_at: 'DESC' },
    });
  }

  // Đánh dấu alert là đã đọc
  async markAlertAsRead(alert_id: number): Promise<void> {
    await this.alertRepo.update(alert_id, { status: 'read' });
  }

  // Dùng cho hệ thống cảnh báo tự động (DigitalSignatureService)
  async createAlert(user_id: number, alert_type: AlertType, message: string): Promise<AlertLog> {
    const alert = this.alertRepo.create({ user_id, alert_type, message });
    return this.alertRepo.save(alert);
  }
}
