import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Booking, BookingStatus } from './booking.entity';

@Injectable()
export class BookingRepository {
  constructor(
    @InjectRepository(Booking, 'bookingConnection')
    private readonly bookingRepo: Repository<Booking>,
  ) {}

  // Tạo mới booking
  async createBooking(data: Partial<Booking>): Promise<Booking> {
    const booking = this.bookingRepo.create(data);
    return this.bookingRepo.save(booking);
  }
  async findByVehicleAndDate(vehicle_id: number, start_date: Date, end_date: Date): Promise<Booking[]> {
    return this.bookingRepo.find({ where: { vehicle_id, start_date, end_date } });
  }

  // Lấy tất cả booking (hoặc lọc sau này)
  async findAll(): Promise<Booking[]> {
    return this.bookingRepo.find();
  }

  async getApprovedBookingsByDate(date: string | Date): Promise<Booking[]> {
    const targetDate = typeof date === 'string' ? new Date(date) : date;

    return this.bookingRepo
      .createQueryBuilder('booking')
      .where('booking.booking_status = :status', { status: BookingStatus.APPROVED })
      .andWhere(':targetDate BETWEEN booking.start_date AND booking.end_date', { targetDate })
      .getMany();
  }

  // Lấy booking theo ID
  async findById(id: number): Promise<Booking | null> {
    return this.bookingRepo.findOne({ where: { booking_id: id } });
  }

  // Lấy booking theo user_id
  async findByUserId(user_id: number): Promise<Booking[]> {
    return this.bookingRepo.find({ where: { user_id } }); // đổi userId nếu entity khác
  }

  // Cập nhật booking
  async updateBooking(id: number, updateData: Partial<Booking>): Promise<Booking> {
    await this.bookingRepo.update(id, updateData);
    const updatedBooking = await this.findById(id);
    if (!updatedBooking) {
      throw new Error(`Booking with id ${id} not found after update.`);
    }
    return updatedBooking;
  }


  // Xóa booking
  async deleteBooking(id: number): Promise<void> {
    await this.bookingRepo.delete(id);
  }
}
