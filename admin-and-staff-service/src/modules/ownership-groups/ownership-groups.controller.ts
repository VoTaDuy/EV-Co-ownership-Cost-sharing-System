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

@Controller('ownership-groups')
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

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.ownershipGroupsService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() dto: UpdateOwnershipGroupDto) {
    return this.ownershipGroupsService.update(id, dto);
  }

  @Delete(':id')
  delete(@Param('id') id: string) {
    return this.ownershipGroupsService.delete(id);
  }

  //RabbitMQ
  @EventPattern('create_ownership_group')
  async handleCreateOwnershipGroup(@Payload() data: CreateOwnershipGroupDto) {
    console.log('📩 Received ownership group:', data);
    return this.ownershipGroupsService.create(data);
  }
}
