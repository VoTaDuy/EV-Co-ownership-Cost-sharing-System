import { Controller, Logger } from '@nestjs/common';
import { EventPattern, Payload } from '@nestjs/microservices';

@Controller()
export class AppController {
  private readonly logger = new Logger(AppController.name);

  @EventPattern('booking_created')
  handleBookingCreated(@Payload() data: any) {
    this.logger.log('Received booking event:', data);
    // Xử lý logic: cập nhật admin dashboard, gửi thông báo, v.v.
  }
}
