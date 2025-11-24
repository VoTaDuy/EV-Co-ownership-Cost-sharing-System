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
import { HttpUserService } from '../common/http-user.service';
import { HttpModule } from '@nestjs/axios';

@Module({
  imports: [TypeOrmModule.forFeature([DigitalSignature, UsageRecord,], 'bookingConnection'),
  AlertModule,
  UsageModule,     
  BookingModule, 
  HttpModule,
  ],
  providers: [DigitalSignatureRepository, DigitalSignatureService, HttpUserService],
  controllers: [DigitalSignatureController],
  exports: [DigitalSignatureService, DigitalSignatureRepository, HttpUserService],
})
export class DigitalSignatureModule {}
