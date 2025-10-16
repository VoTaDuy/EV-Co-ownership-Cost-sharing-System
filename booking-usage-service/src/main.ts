import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ConfigService } from '@nestjs/config';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // Cấu hình Swagger
   const config = new DocumentBuilder()
    .setTitle('Booking & Usage Service')
    .setDescription('API documentation for Booking microservice')
    .setVersion('1.0')
    .addTag('booking')
    .build();
  
    const document = SwaggerModule.createDocument(app, config);
    SwaggerModule.setup('api/docs', app, document); // => truy cập ở http://localhost:port/api/docs
  
  // Lấy ConfigService của NestJS để đọc env
    const configService = app.get(ConfigService);
    const dbHost = configService.get<string>('DB_HOST');
    const port = process.env.PORT ?? 3000;


  await app.listen(port);
  console.log(`Server đang chạy ở: http://localhost:${port}`);
  console.log(`API Swagger docs đang khả dụng: http://localhost:${port}/api/docs`);
  console.log(`DB host: ${dbHost}`); // In ra giá trị của DB_HOST từ file .env
}
bootstrap();
