import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Booking } from './booking.entity';
import { BookingService } from './booking.service';
import { BookingController } from './booking.controller';
import { BookingRepository } from './booking.repository';
import { ConflictLogModule } from '../conflict_log/conflict-log.module';
import { UsageModule } from '../usage/usage.module';
import { HttpUserService } from '../common/http-user.service';
import { HttpAdminService } from '../common/http-admin.service';  
import { HttpModule } from '@nestjs/axios';

@Module({
  imports: [TypeOrmModule.forFeature([Booking])
    , ConflictLogModule
    , UsageModule
    , HttpModule
  ],
  controllers: [BookingController],
  providers: [BookingService, BookingRepository, HttpUserService, HttpAdminService],
  exports: [BookingService, BookingRepository, HttpUserService, HttpAdminService],
})
export class BookingModule {}
