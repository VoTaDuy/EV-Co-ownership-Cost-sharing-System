import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common/pipes';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,
      forbidNonWhitelisted: true,
      transform: true,
    }),
  );
  // ✅ FIX CORS - CHO PHÉP TẤT CẢ ORIGINS (dev only)
  app.enableCors({
    origin: true, // Cho phép tất cả origins
    credentials: true, // Cho phép cookies/auth
  });
  await app.listen(3000);
}
void bootstrap();
