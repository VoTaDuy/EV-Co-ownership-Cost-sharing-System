import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { DatabaseModule } from './config/database.module';
import { BookingModule } from './booking/booking.module';
import { UsageModule } from './usage/usage.module';
import { DigitalSignatureModule } from './digital_signature/digital-signature.module';
import { ConflictLogModule } from './conflict_log/conflict-log.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    DatabaseModule,
    BookingModule,
    UsageModule,
    DigitalSignatureModule,
    ConflictLogModule,
  ],
})
export class AppModule {}
