import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Booking } from './booking.entity';

@Injectable()
export class BookingRepository {
  constructor(
    @InjectRepository(Booking)
    private readonly bookingRepo: Repository<Booking>,
  ) {}

  // Tạo mới booking
  async createBooking(data: Partial<Booking>): Promise<Booking> {
    const booking = this.bookingRepo.create(data);
    return this.bookingRepo.save(booking);
  }

  // Lấy tất cả booking (hoặc lọc sau này)
  async findAll(): Promise<Booking[]> {
    return this.bookingRepo.find();
  }

  // Lấy booking theo ID
  async findById(id: string): Promise<Booking | null> {
    return this.bookingRepo.findOne({ where: { booking_id: id } });
  }

  // Cập nhật booking
  async updateBooking(id: string, updateData: Partial<Booking>): Promise<Booking> {
    await this.bookingRepo.update(id, updateData);
    const updatedBooking = await this.findById(id);
    if (!updatedBooking) {
      throw new Error(`Booking with id ${id} not found after update.`);
    }
    return updatedBooking;
  }

  // Xóa booking
  async deleteBooking(id: string): Promise<void> {
    await this.bookingRepo.delete(id);
  }
}
