import { Injectable } from '@nestjs/common';
import {
  ClientProxyFactory,
  Transport,
  ClientProxy,
} from '@nestjs/microservices';

@Injectable()
export class AppService {
  private client: ClientProxy;

  constructor() {
    this.client = ClientProxyFactory.create({
      transport: Transport.RMQ,
      options: {
        urls: ['amqp://guest:guest@rabbitmq:5672'],
        queue: 'admin_queue', // gửi đến queue của service nhận
        queueOptions: {
          durable: false,
        },
      },
    });
  }

  sendBookingData(data: any) {
    return this.client.emit('booking_created', data); // emit = fire-and-forget
  }
}
