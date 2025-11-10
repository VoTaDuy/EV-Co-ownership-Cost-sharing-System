import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
} from '@nestjs/common';
import { ContractsTemplateService } from './contracts-template.service';
import { ContractsTemplate } from './contracts-template.entity';

@Controller('admin/contracts-template')
export class ContractsTemplateController {
  constructor(private readonly contractsService: ContractsTemplateService) {}

  @Get()
  async findAll(): Promise<ContractsTemplate[]> {
    return this.contractsService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: string): Promise<ContractsTemplate> {
    return this.contractsService.findOne(id);
  }

  @Post()
  async create(
    @Body() body: Partial<ContractsTemplate>,
  ): Promise<ContractsTemplate> {
    return this.contractsService.create(body);
  }

  @Put(':id')
  async update(
    @Param('id') id: string,
    @Body() body: Partial<ContractsTemplate>,
  ): Promise<ContractsTemplate> {
    return this.contractsService.update(id, body);
  }

  @Delete(':id')
  async remove(@Param('id') id: string): Promise<void> {
    return this.contractsService.remove(id);
  }
}
