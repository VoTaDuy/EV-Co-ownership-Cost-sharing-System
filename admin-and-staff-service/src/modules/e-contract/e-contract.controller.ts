import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
} from '@nestjs/common';
import { EContactsService } from './e-contract.service';
import { CreateEContactDto, UpdateEContactDto } from './e-contract.dto';

@Controller('admin/e-contacts')
export class EContactsController {
  constructor(private readonly service: EContactsService) {}

  @Post()
  create(@Body() dto: CreateEContactDto) {
    return this.service.create(dto);
  }

  @Get()
  findAll() {
    return this.service.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.service.findOne(id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() dto: UpdateEContactDto) {
    return this.service.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.service.remove(id);
  }
}
