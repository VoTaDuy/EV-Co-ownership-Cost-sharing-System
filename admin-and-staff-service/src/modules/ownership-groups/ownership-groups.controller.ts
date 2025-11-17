import {
  Controller,
  Get,
  Post,
  Body,
  Param,
  Patch,
  Delete,
} from '@nestjs/common';
import { OwnershipGroupsService } from './ownership-groups.service';
import {
  CreateOwnershipGroupDto,
  UpdateOwnershipGroupDto,
} from './ownership-groups.dto';
import { EventPattern, Payload } from '@nestjs/microservices';

@Controller('admin/ownership-groups')
export class OwnershipGroupsController {
  constructor(
    private readonly ownershipGroupsService: OwnershipGroupsService,
  ) {}

  @Post()
  create(@Body() dto: CreateOwnershipGroupDto) {
    return this.ownershipGroupsService.create(dto);
  }

  @Get()
  findAll() {
    return this.ownershipGroupsService.findAll();
  }
  // Lấy tất cả group của một user
  @Get('user/:userId/groups')
  getGroupsByUser(@Param('userId') userId: number) {
    return this.ownershipGroupsService.getGroupsByUser(Number(userId));
  }

  @Get(':id')
  findOne(@Param('id') id: number) {
    return this.ownershipGroupsService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: number, @Body() dto: UpdateOwnershipGroupDto) {
    return this.ownershipGroupsService.update(id, dto);
  }

  @Delete(':id')
  delete(@Param('id') id: number) {
    return this.ownershipGroupsService.delete(id);
  }

  //RabbitMQ
  @EventPattern('create_ownership_group_queue') // PHẢI KHỚP với queue trong main.ts
  async handleCreateOwnershipGroup(@Payload() data: CreateOwnershipGroupDto) {
    console.log('NHẬN ĐƯỢC TỪ RABBITMQ:', data);
    return this.ownershipGroupsService.create(data);
  }
}
