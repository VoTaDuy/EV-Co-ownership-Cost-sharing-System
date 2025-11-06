import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { AlertLog, AlertType } from './alert.entity';

@Injectable()
export class AlertRepository {
  constructor(
    @InjectRepository(AlertLog)
    private readonly repo: Repository<AlertLog>,
  ) {}

  async createAlert(user_id: string, alert_type: AlertType, message: string): Promise<AlertLog> {
    const alert = this.repo.create({
      user_id,
      alert_type,
      message,
      status: 'unread',
    });
    return this.repo.save(alert);
  }

  async findAll(): Promise<AlertLog[]> {
    return this.repo.find({ order: { created_at: 'DESC' } });
  }

  async findByUser(user_id: string): Promise<AlertLog[]> {
    return this.repo.find({
      where: { user_id },
      order: { created_at: 'DESC' },
    });
  }

  async markAsRead(alert_id: string): Promise<void> {
    await this.repo.update(alert_id, { status: 'read' });
  }

}
