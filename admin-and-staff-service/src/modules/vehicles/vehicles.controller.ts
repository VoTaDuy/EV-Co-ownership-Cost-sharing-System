import { VehiclesService } from './vehicles.service';
import { Vehicle } from './vehicles.entity';
import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Body,
  Param,
} from '@nestjs/common';

@Controller('vehicles')
export class VehiclesController {
  constructor(private readonly vehiclesService: VehiclesService) {}

  @Get()
  findAll(): Promise<Vehicle[]> {
    return this.vehiclesService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.vehiclesService.findOne(id);
  }

  @Post()
  create(@Body() data: Partial<Vehicle>) {
    return this.vehiclesService.create(data);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() data: Partial<Vehicle>) {
    return this.vehiclesService.update(id, data);
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

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.vehiclesService.remove(id);
  }
}
