import { Module } from '@nestjs/common';
import { TypeOrmModule, TypeOrmModuleOptions } from '@nestjs/typeorm';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { Booking } from '../booking/booking.entity';
import { UsageRecord } from '../usage/usage.entity';
import { DigitalSignature } from '../digital-signature/digital-signature.entity';
import { AlertLog } from '../alert/alert.entity';
import { ConflictLog } from '../conflict_log/conflict-log.entity';
import { Vehicle } from '../vehicles/vehicle.entity';
import { OwnershipGroup } from '../group-member/ownership-group.entity';
import { GroupMember } from '../group-member/group-member.entity';

@Module({
  imports: [
    ConfigModule, // để ConfigService có sẵn

    // --- Booking DB connection ---
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      inject: [ConfigService],
      name: 'bookingConnection', // đặt tên connection
      useFactory: (configService: ConfigService): TypeOrmModuleOptions => ({
        type: 'mysql',
        host: configService.get<string>('DB_HOST'),
        port: parseInt(configService.get<string>('DB_PORT') ?? '3306', 10),
        username: configService.get<string>('DB_USER'),
        password: configService.get<string>('DB_PASS'),
        database: configService.get<string>('DB_NAME'),
        entities: [Booking, UsageRecord, DigitalSignature, AlertLog, ConflictLog],
        synchronize: true, // dev only
        logging: true,
      }),
    }),

    // --- DW DB connection ---
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      inject: [ConfigService],
      name: 'dwConnection', // đặt tên connection
      useFactory: (configService: ConfigService): TypeOrmModuleOptions => ({
        type: 'mysql',
        host: configService.get<string>('DW_HOST') ?? 'dw_mysql',
        port: parseInt(configService.get<string>('DW_PORT') ?? '3306', 10),
        username: configService.get<string>('DW_USER') ?? 'root',
        password: configService.get<string>('DW_PASS') ?? '123456',
        database: configService.get<string>('DW_NAME') ?? 'data_warehouse',
        entities: [Vehicle, OwnershipGroup, GroupMember],
        synchronize: true,
        logging: true,
      }),
    }),
  ],
})
export class DatabaseModule {}
