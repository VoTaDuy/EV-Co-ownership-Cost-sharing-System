import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { DatabaseModule } from './config/database.module';
import { BookingModule } from './booking/booking.module';
import { UsageModule } from './usage/usage.module';
import { DigitalSignatureModule } from './digital-signature/digital-signature.module';
import { ConflictLogModule } from './conflict_log/conflict-log.module';
import { AlertModule } from './alert/alert.module';
import { VehicleModule } from './vehicles/vehicle.module';
import { AppService } from './app.service';

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
    AlertModule,
    VehicleModule,
  ],
  providers: [AppService],
})
export class AppModule {}
