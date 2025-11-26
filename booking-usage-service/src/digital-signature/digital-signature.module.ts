import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DigitalSignature } from './digital-signature.entity';
import { UsageRecord } from '../usage/usage.entity';
import { DigitalSignatureRepository } from './digital-signature.repository';
import { DigitalSignatureService } from './digital-signature.service';
import { DigitalSignatureController } from './digital-signature.controller';
import { AlertModule } from '../alert/alert.module';
import { UsageModule } from '../usage/usage.module';
import { BookingModule } from '../booking/booking.module';

@Module({
  imports: [TypeOrmModule.forFeature([DigitalSignature, UsageRecord,], 'bookingConnection'),
  AlertModule,
  UsageModule,     
  BookingModule, 
  ],
  providers: [DigitalSignatureRepository, DigitalSignatureService],
  controllers: [DigitalSignatureController],
  exports: [DigitalSignatureService, DigitalSignatureRepository],
})
export class DigitalSignatureModule {}
