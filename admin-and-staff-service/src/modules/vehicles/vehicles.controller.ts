import { VehiclesService } from './vehicles.service';
import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Body,
  Param,
  Patch,
} from '@nestjs/common';
import { CreateVehicleDto, UpdateVehicleDto } from './vehicles.dto';

@Controller('vehicles')
export class VehiclesController {
  constructor(private readonly vehiclesService: VehiclesService) {}

  @Post()
  create(@Body() dto: CreateVehicleDto) {
    return this.vehiclesService.create(dto);
  }

  @Get()
  findAll() {
    return this.vehiclesService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.vehiclesService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() dto: UpdateVehicleDto) {
    return this.vehiclesService.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.vehiclesService.delete(id);
  }
  // Cập nhật ảnh xe
  @Put(':id/images')
  updateImages(
    @Param('id') id: string,
    @Body() body: { image_url: string; spec_image_urls?: string[] },
  ) {
    const { image_url, spec_image_urls } = body;
    return this.vehiclesService.updateImages(id, image_url, spec_image_urls);
  }
}
