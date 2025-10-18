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
import { OwnershipGroup } from './ownership-groups.entity';

@Controller('ownership-groups')
export class OwnershipGroupsController {
  constructor(
    private readonly ownershipGroupsService: OwnershipGroupsService,
  ) {}

  @Post()
  create(@Body() data: Partial<OwnershipGroup>) {
    return this.ownershipGroupsService.create(data);
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
  update(@Param('id') id: string, @Body() data: Partial<OwnershipGroup>) {
    return this.ownershipGroupsService.update(id, data);
  }

  @Delete(':id')
  delete(@Param('id') id: string) {
    return this.ownershipGroupsService.delete(id);
  }
}
