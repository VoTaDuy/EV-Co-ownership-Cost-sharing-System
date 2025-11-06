import { Injectable, NotFoundException } from '@nestjs/common';
import { BookingRepository } from './booking.repository';
import { Booking } from './booking.entity';
import { ConflictLogService } from '../conflict_log/conflict-log.service';
import { BookingStatus } from './booking.entity';
import { UsageRepository } from '../usage/usage.repository';

@Injectable()
export class BookingService {
  constructor(
    private readonly bookingRepository: BookingRepository,
    private readonly conflictLogService: ConflictLogService,
    private readonly UsageRepository: UsageRepository,
  ) {}

  //Tạo booking mới
  async createBooking(data: Partial<Booking>): Promise<Booking> {
  if (!data.vehicle_id || !data.start_date || !data.end_date || !data.check_in_time || !data.check_out_time) {
      throw new Error('Thiếu thông tin bắt buộc: vehicle_id, start_date, end_date, check_in_time, check_out_time');
    }

    const existingBookings = await this.bookingRepository.findByVehicleAndDate(
      data.vehicle_id,
      data.start_date,
      data.end_date,
    );

    const hasConflict = existingBookings.some((b) => {
      const newStart = new Date(data.start_date!);
      const newEnd = new Date(data.end_date!);
      const existingStart = new Date(b.start_date!);
      const existingEnd = new Date(b.end_date!);

      // Kiểm tra nếu 2 khoảng thời gian giao nhau
      return newStart <= existingEnd && newEnd >= existingStart;
    });

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

  const updated = await this.bookingRepository.updateBooking(id, data);

  // ✅ Nếu booking vừa chuyển sang approved, tạo usage record
  if (
    existing.booking_status !== BookingStatus.APPROVED &&
    data.booking_status === BookingStatus.APPROVED
  ) {
    const usage = await this.UsageRepository.findByBookingId(updated.booking_id);
    if (!usage) {
      await this.UsageRepository.createUsage({
        booking_id: updated.booking_id,
        user_id: updated.user_id,
        vehicle_id: updated.vehicle_id,
        start_date: updated.start_date,
        end_date: updated.end_date,
      });
      console.log(` Đã tạo usage record cho booking ${updated.booking_id}`);
    }
  }

    return updated;
  }

  //Xóa booking
  async deleteBooking(id: string): Promise<void> {
    const existing = await this.getBookingById(id);
    if (!existing) throw new NotFoundException('Không tìm thấy booking');
    return this.bookingRepository.deleteBooking(id);
  }
}
