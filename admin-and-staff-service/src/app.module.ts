import { OwnershipGroupsService } from './modules/ownership-groups/ownership-groups.service';
import { OwnershipGroupsController } from './modules/ownership-groups/ownership-groups.controller';
import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { VehiclesModule } from './modules/vehicles/vehicles.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ServiceTasksModule } from './modules/service-tasks/service-tasks.module';
import { OwnershipGroupsModule } from './modules/ownership-groups/ownership-groups.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'localhost',
      port: 5432,
      username: 'postgres',
      password: '123456', // nhập mật khẩu thật của bạn
      database: 'postgres', // tên database bạn tạo
      autoLoadEntities: true,
      synchronize: true, // chỉ bật khi dev
    }),
    VehiclesModule,
    ServiceTasksModule,
    OwnershipGroupsModule,
  ],
  controllers: [OwnershipGroupsController, AppController],
  providers: [OwnershipGroupsService, AppService],
})
export class AppModule {}
