import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UsageRecord } from './usage.entity';
import { UsageRepository } from './usage.repository';
import { UsageService } from './usage.service';
import { UsageController } from './usage.controller';

@Module({
  imports: [TypeOrmModule.forFeature([UsageRecord])],
  providers: [UsageRepository, UsageService],
  controllers: [UsageController],
  exports: [UsageService, UsageRepository],
})
export class UsageModule {}
