import { OwnershipGroupsService } from './modules/ownership-groups/ownership-groups.service';
import { OwnershipGroupsController } from './modules/ownership-groups/ownership-groups.controller';
import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { VehiclesModule } from './modules/vehicles/vehicles.module';
import { ServiceTasksModule } from './modules/service-tasks/service-tasks.module';
import { OwnershipGroupsModule } from './modules/ownership-groups/ownership-groups.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: process.env.DATABASE_HOST,
      port: parseInt(process.env.DATABASE_PORT || '5432', 10),
      username: process.env.DATABASE_USER,
      password: process.env.DATABASE_PASSWORD || '123456',
      database: process.env.DATABASE_NAME,
      autoLoadEntities: true,
      synchronize: true,
    }),
    VehiclesModule,
    ServiceTasksModule,
    OwnershipGroupsModule,
  ],
  controllers: [OwnershipGroupsController, AppController],
  providers: [OwnershipGroupsService, AppService],
})
export class AppModule {}
