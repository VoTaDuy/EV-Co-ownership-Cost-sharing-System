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

  /**
   * Lấy timeline xe cho 1 tuần (Thứ 2 → Chủ nhật) của startDate
   */
  async getVehicleTimeline(startDate: string): Promise<any[]> {
    const start = new Date(startDate);

    // Xác định Thứ 2 của tuần
    const dayOfWeek = start.getDay(); // 0: Chủ nhật, 1: Thứ 2, ...
    const monday = new Date(start);
    monday.setDate(start.getDate() - (dayOfWeek === 0 ? 6 : dayOfWeek - 1));

    // Tạo array 7 ngày Thứ 2 → Chủ nhật
    const weekDays = Array.from({ length: 7 }, (_, i) => {
      const d = new Date(monday);
      d.setDate(monday.getDate() + i);
      return d;
    });

    // Lấy tất cả xe
    const vehicles = await this.vehicleRepo.find({ where: { is_available: true } });

    // Lấy booking approved trong tuần
    const bookings = await this.bookingRepo.find({
      where: {
        booking_status: BookingStatus.APPROVED,
        start_date: Between(weekDays[0], weekDays[6]),
      },
    });

    // Map timeline từng xe
    return vehicles.map(v => {
      const timeline = weekDays.map(day => {
        const dayStr = day.toISOString().split('T')[0];
        const hasBooking = bookings.some(
          b => b.vehicle_id === v.vehicle_id &&
               new Date(b.start_date) <= day &&
               new Date(b.end_date) >= day
        );
        return { date: dayStr, status: hasBooking ? 'used' : 'available' };
      });

      return { vehicle_id: v.vehicle_id, vehicle_name: v.vehicle_name, timeline };
    });
  }
}
