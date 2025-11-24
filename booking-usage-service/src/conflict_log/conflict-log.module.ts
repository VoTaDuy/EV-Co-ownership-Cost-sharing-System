import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConflictLog } from './conflict-log.entity';
import { ConflictLogRepository } from './conflict-log.repository';
import { ConflictLogService } from './conflict-log.service';
import { ConflictLogController } from './conflict-log.controller';

@Module({
  imports: [TypeOrmModule.forFeature([ConflictLog], 'bookingConnection')],
  controllers: [ConflictLogController],
  providers: [ConflictLogRepository, ConflictLogService],
  exports: [ConflictLogService], // Để BookingService có thể inject và tạo conflict
})
export class ConflictLogModule {}
