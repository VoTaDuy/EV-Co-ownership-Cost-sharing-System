import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { DatabaseModule } from './config/database.module';
import { BookingModule } from './booking/booking.module';
import { UsageModule } from './usage/usage.module';
import { DigitalSignatureModule } from './digital_signature/digital-signature.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    DatabaseModule,
    BookingModule,
    UsageModule,
    DigitalSignatureModule,
  ],
})
export class AppModule {}
