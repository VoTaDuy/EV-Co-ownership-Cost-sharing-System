import { Injectable, NotFoundException } from '@nestjs/common';
import { BookingRepository } from './booking.repository';
import { Booking } from './booking.entity';

@Injectable()
export class BookingService {
  constructor(private readonly bookingRepository: BookingRepository) {}

  //Tạo booking mới
  async createBooking(data: Partial<Booking>): Promise<Booking> {
    return this.bookingRepository.createBooking(data);
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
