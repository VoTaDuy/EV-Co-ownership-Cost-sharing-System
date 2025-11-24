import { Injectable } from '@nestjs/common';
import { AlertLog, AlertType } from './alert.entity';
import { AlertRepository } from './alert.repository';

@Injectable()
export class AlertService {
  constructor(private readonly alertRepo: AlertRepository) {}

  async getAllAlerts(type?: AlertType): Promise<AlertLog[]> {
    if (type) return this.alertRepo.findByType(type);
    return this.alertRepo.findAll();
  }

  async getAlertsByUser(user_id: number): Promise<AlertLog[]> {
    return this.alertRepo.findByUser(user_id);
  }

  async createAlert(user_id: number, alert_type: AlertType, message: string): Promise<AlertLog> {
    return this.alertRepo.createAlert(user_id, alert_type, message);
  }
}
