import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';

import { AppController } from './app.controller';
import { AppService } from './app.service';

import { VehiclesModule } from './modules/vehicles/vehicles.module';
import { ServiceTasksModule } from './modules/service-tasks/service-tasks.module';
import { OwnershipGroupsModule } from './modules/ownership-groups/ownership-groups.module';
import { OwnershipGroupsService } from './modules/ownership-groups/ownership-groups.service';
import { OwnershipGroupsController } from './modules/ownership-groups/ownership-groups.controller';
import { CloudinaryModule } from './modules/cloudinary/cloudinary.module';
import { ReportModule } from './modules/report/reports.module';
import { ContractsTemplateModule } from './modules/contracts-template/contracts-template.module';
import { GroupMembersModule } from './modules/group-members/group-members.module';
import { EContactsModule } from './modules/e-contract/e-contract.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
      envFilePath: '.env',
    }),

    TypeOrmModule.forRoot({
      type: 'postgres',
      host: process.env.DATABASE_HOST,
      port: parseInt(process.env.DATABASE_PORT || '5433', 10),
      username: process.env.DATABASE_USER,
      password: process.env.DATABASE_PASSWORD || '123456',
      database: process.env.DATABASE_NAME,
      autoLoadEntities: true,
      synchronize: true,
    }),
    VehiclesModule,
    ServiceTasksModule,
    OwnershipGroupsModule,
    CloudinaryModule,
    ReportModule,
    ContractsTemplateModule,
    GroupMembersModule,
    EContactsModule,
  ],
  controllers: [AppController, OwnershipGroupsController],
  providers: [AppService, OwnershipGroupsService],
})
export class AppModule {}
