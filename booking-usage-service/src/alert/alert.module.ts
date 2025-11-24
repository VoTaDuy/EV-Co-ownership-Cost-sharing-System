import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AlertLog } from './alert.entity';
import { AlertService } from './alert.service';
import { AlertController } from './alert.controller';
import { AlertRepository } from './alert.repository';

@Module({
  imports: [TypeOrmModule.forFeature([AlertLog], 'bookingConnection')],
  providers: [AlertRepository, AlertService],
  controllers: [AlertController],
  exports: [AlertRepository, AlertService],
})
export class AlertModule {}
