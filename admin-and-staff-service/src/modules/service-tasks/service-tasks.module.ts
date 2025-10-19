import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ServiceTasksService } from './service-tasks.service';
import { ServiceTasksController } from './service-tasks.controller';
import { ServiceTask } from './service-tasks.entity';

@Module({
  imports: [TypeOrmModule.forFeature([ServiceTask])],
  controllers: [ServiceTasksController],
  providers: [ServiceTasksService],
  exports: [ServiceTasksService],
})
export class ServiceTasksModule {}
