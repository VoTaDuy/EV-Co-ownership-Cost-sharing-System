import { Injectable, NotFoundException } from '@nestjs/common';
import { BookingRepository } from './booking.repository';
import { Booking } from './booking.entity';
import { ConflictLogService } from '../conflict_log/conflict-log.service';

@Injectable()
export class BookingService {
  constructor(
    private readonly bookingRepository: BookingRepository,
    private readonly conflictLogService: ConflictLogService,
  ) {}

  //Tạo booking mới
  async createBooking(data: Partial<Booking>): Promise<Booking> {
  if (!data.vehicle_id || !data.booking_date || !data.start_time || !data.end_time) {
      throw new Error('Thiếu thông tin bắt buộc: vehicle_id, booking_date, start_time, end_time');
    }

    const existingBookings = await this.bookingRepository.findByVehicleAndDate(
      data.vehicle_id,
      data.booking_date,
    );

    const hasConflict = existingBookings.some(
      (b) =>
        (data.start_time! >= b.start_time && data.start_time! < b.end_time) ||
        (data.end_time! > b.start_time && data.end_time! <= b.end_time),
    );

    // Đầu tiên tạo booking
    const booking = await this.bookingRepository.createBooking(data);

    // Sau đó mới tạo conflict nếu có trùng
    if (hasConflict) {
      await this.conflictLogService.createConflict(
        booking.booking_id,
        `Phát hiện trùng lịch đặt xe cho vehicle_id ${booking.vehicle_id}`,
      );
    }

    return booking;
  }

  //Lấy tất cả booking
  async getAllBookings(): Promise<Booking[]> {
    return this.bookingRepository.findAll();
  }

  //Lấy booking theo ID
  async getBookingById(id: string): Promise<Booking> {
    const booking = await this.bookingRepository.findById(id);
    if (!booking) throw new NotFoundException(`Booking ${id} không tồn tại`);
    return booking;
  }

  //Cập nhật booking
  async updateBooking(id: string, data: Partial<Booking>): Promise<Booking> {
    const existing = await this.getBookingById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy booking');
    return this.bookingRepository.updateBooking(id, data);
  }

  //Xóa booking
  async deleteBooking(id: string): Promise<void> {
    const existing = await this.getBookingById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy booking');
    return this.bookingRepository.deleteBooking(id);
  }
}
