import { Controller, Get, Post, Put, Delete, Body, Param } from '@nestjs/common';
import { BookingService } from './booking.service';
import { Booking } from './booking.entity';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { CreateBookingDto } from './dto/createBooking.dto';
import { UpdateBookingDto } from './dto/update-booking.dto';
import { BookingIdDto } from './dto/booking-Id.dto';

@ApiTags('booking')
@Controller('bookings')
export class BookingController {
  constructor(private readonly bookingService: BookingService) {}

  
  @Post()
  @ApiOperation({ summary: 'Tạo booking (đặt lịch xe) mới' })
  async createBooking(@Body() data: CreateBookingDto) {
    return this.bookingService.createBooking(data);
  }

  
  @Get()
  @ApiOperation({ summary: 'Lấy danh sách booking (đặt lịch xe) đang khả dụng' })
  async getAllBookings() {
    return this.bookingService.getAllBookings();
  }

  @Get(':id')
  @ApiOperation({ summary: 'Lấy booking (đặt lịch xe) theo ID của booking đó' })
  async getBookingById(@Param('id') id: string) {
    return this.bookingService.getBookingById(id);
  }

  @Put(':id')
  @ApiOperation({ summary: 'Cập nhật thông tin booking (đặt lịch xe)' })
  async updateBooking(@Param('id') id: string, @Body() data: Partial<Booking>) {
    return this.bookingService.updateBooking(id, data);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Xóa booking (đặt lịch xe)' })
  async deleteBooking(@Param('id') id: string) {
    return this.bookingService.deleteBooking(id);
  }
}
