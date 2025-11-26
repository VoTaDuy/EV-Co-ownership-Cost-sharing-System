// src/vehicles/vehicle.service.ts
import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Vehicle } from './vehicle.entity';
import { Booking, BookingStatus } from '../booking/booking.entity';
import { Repository, Between } from 'typeorm';

@Injectable()
export class VehicleService {
  constructor(
    @InjectRepository(Vehicle, 'dwConnection')
    private readonly vehicleRepo: Repository<Vehicle>,

    @InjectRepository(Booking, 'bookingConnection')
    private readonly bookingRepo: Repository<Booking>,
  ) {}

  async getVehicleGroupTimeline(vehicleId: number, startDate: string) {
    const start = new Date(startDate);

    // --- Tính thứ 2 tuần ---
    const dayOfWeek = start.getDay();
    const monday = new Date(start);
    monday.setDate(start.getDate() - (dayOfWeek === 0 ? 6 : dayOfWeek - 1));

    const weekDays = Array.from({ length: 7 }, (_, i) => {
      const d = new Date(monday);
      d.setDate(monday.getDate() + i);
      return d;
    });

    const vehicle = await this.vehicleRepo.findOne({
      where: { vehicle_id: vehicleId },
    });

    if (!vehicle) {
      throw new Error(`Vehicle ${vehicleId} not found`);
    }

    const bookings = await this.bookingRepo.find({
      where: {
        booking_status: BookingStatus.APPROVED,
        vehicle_id: vehicleId,
        start_date: Between(weekDays[0], weekDays[6]),
      },
    });

    const timeline = weekDays.map(day => {
      const dayStr = day.toISOString().split('T')[0];
      const isUsed = bookings.some(
        b =>
          new Date(b.start_date) <= day &&
          new Date(b.end_date) >= day
      );

      return {
        date: dayStr,
        status: isUsed ? "used" : "available",
      };
    });

    return {
      vehicle_id: vehicle.vehicle_id,
      vehicle_name: vehicle.vehicle_name,
      timeline,
    };
  }

  async getVehiclesStatusSummaryByDate(dateString: string) {
    const targetDate = new Date(dateString);
    targetDate.setHours(0, 0, 0, 0);

    const nextDate = new Date(targetDate);
    nextDate.setDate(targetDate.getDate() + 1);

    // 1) Lấy tất cả xe
    const vehicles = await this.vehicleRepo.find();

    // 2) Lấy tất cả booking APPROVED trùng ngày
    const bookings = await this.bookingRepo.find({
      where: {
        booking_status: BookingStatus.APPROVED,
        start_date: Between(targetDate, nextDate),
      },
    });

    // 3) Đếm số xe theo status
    let availableCount = 0;
    let usedCount = 0;

    vehicles.forEach(v => {
      const isUsed = bookings.some(b => b.vehicle_id === v.vehicle_id);
      if (isUsed) usedCount++;
      else availableCount++;
    });

    return {
      total: vehicles.length,
      available: availableCount,
      used: usedCount,
    };
  }
}
