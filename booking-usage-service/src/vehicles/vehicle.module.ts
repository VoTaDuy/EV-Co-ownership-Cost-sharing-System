// src/vehicles/vehicle.module.ts
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Vehicle } from './vehicle.entity';
import { VehicleService } from './vehicle.service';
import { VehicleController } from './vehicle.controller';
import { Booking } from '../booking/booking.entity';

@Module({
  imports: [
    TypeOrmModule.forFeature([Vehicle], 'dwConnection'), // Sử dụng connection DW
    TypeOrmModule.forFeature([Booking], 'bookingConnection'), // Sử dụng connection Booking
  ],
  providers: [VehicleService],
  controllers: [VehicleController],
})
export class VehicleModule {}
