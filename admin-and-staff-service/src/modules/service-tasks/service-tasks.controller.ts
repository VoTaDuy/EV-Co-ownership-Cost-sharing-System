import {
  Controller,
  Get,
  Post,
  Param,
  Body,
  Put,
  Delete,
  Patch,
} from '@nestjs/common';
import { ServiceTasksService } from './service-tasks.service';
import { ServiceTask } from './service-tasks.entity';
import {
  CreateServiceTaskDto,
  UpdateServiceTaskDto,
} from './service-tasks.dto';

@Controller('service-tasks')
export class ServiceTasksController {
  constructor(private readonly serviceTasksService: ServiceTasksService) {}

  @Post()
  create(@Body() dto: CreateServiceTaskDto) {
    return this.serviceTasksService.create(dto);
  }

  @Get()
  findAll() {
    return this.serviceTasksService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.serviceTasksService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() dto: UpdateServiceTaskDto) {
    return this.serviceTasksService.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.serviceTasksService.delete(id);
  }

  // Cập nhật trạng thái (pending → in_progress → completed, v.v.)
  @Put(':id/status')
  updateStatus(
    @Param('id') id: string,
    @Body('status') status: ServiceTask['status'],
  ) {
    return this.serviceTasksService.updateStatus(id, status);
  }
}
