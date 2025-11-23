import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';
import { MicroserviceOptions, Transport } from '@nestjs/microservices';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // // ✅ Cấu hình RabbitMQ microservice
  // app.connectMicroservice<MicroserviceOptions>({
  //   transport: Transport.RMQ,
  //   options: {
  //     urls: [
  //       `amqp://${process.env.RABBITMQ_HOST || 'rabbitmq'}:${process.env.RABBITMQ_PORT || 5672}`,
  //     ],
  //     queue: 'ownership_queue',
  //     queueOptions: {
  //       durable: false,
  //     },
  //   },
  // });

  // ✅ Global pipes
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,
      forbidNonWhitelisted: true,
      transform: true,
    }),
  );



  // ✅ Chạy song song HTTP và Microservice
  await app.startAllMicroservices();
  await app.listen(3000);

  console.log('🚀 App is running on http://localhost:3000');
  console.log('📡 Connected to RabbitMQ');
}

void bootstrap();
