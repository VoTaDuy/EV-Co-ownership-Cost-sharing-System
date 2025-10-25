import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DigitalSignature } from './digital-signature.entity';
import { UsageRecord } from '../usage/usage.entity';
import { DigitalSignatureRepository } from './digital-signature.repository';
import { DigitalSignatureService } from './digital-signature.service';
import { DigitalSignatureController } from './digital-signature.controller';

@Module({
  imports: [TypeOrmModule.forFeature([DigitalSignature, UsageRecord])],
  providers: [DigitalSignatureRepository, DigitalSignatureService],
  controllers: [DigitalSignatureController],
})
export class DigitalSignatureModule {}
