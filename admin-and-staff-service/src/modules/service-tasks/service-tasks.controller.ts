import {
  Controller,
  Get,
  Post,
  Param,
  Body,
  Put,
  Delete,
} from '@nestjs/common';
import { ServiceTasksService } from './service-tasks.service';
import { ServiceTask } from './service-tasks.entity';

@Controller('service-tasks')
export class ServiceTasksController {
  constructor(private readonly serviceTasksService: ServiceTasksService) {}

  @Get()
  findAll(): Promise<ServiceTask[]> {
    return this.serviceTasksService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.serviceTasksService.findOne(id);
  }

  @Post()
  create(@Body() data: Partial<ServiceTask>) {
    return this.serviceTasksService.create(data);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() data: Partial<ServiceTask>) {
    return this.serviceTasksService.update(id, data);
  }

  // Cập nhật trạng thái (pending → in_progress → completed, v.v.)
  @Put(':id/status')
  updateStatus(
    @Param('id') id: string,
    @Body('status') status: ServiceTask['status'],
  ) {
    return this.serviceTasksService.updateStatus(id, status);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.serviceTasksService.remove(id);
  }
}
