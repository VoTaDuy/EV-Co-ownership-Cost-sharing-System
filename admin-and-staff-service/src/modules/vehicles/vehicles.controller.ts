import {
  Controller,
  Get,
  Post,
  Delete,
  Body,
  Param,
  Patch,
  UseInterceptors,
  UploadedFiles,
  ParseIntPipe,
} from '@nestjs/common';

import {
  AnyFilesInterceptor,
  FilesInterceptor,
} from '@nestjs/platform-express';
import { VehiclesService } from './vehicles.service';
import { CloudinaryService } from '../cloudinary/cloudinary.service';
import { CreateVehicleDto, UpdateVehicleDto } from './vehicles.dto';

@Controller('admin/vehicles')
export class VehiclesController {
  constructor(
    private readonly vehiclesService: VehiclesService,
    private readonly cloudinaryService: CloudinaryService,
  ) {}

  // ============================
  // CREATE VEHICLE
  // ============================
  @Post()
  @UseInterceptors(AnyFilesInterceptor())
  async createVehicle(
    @UploadedFiles() files: Express.Multer.File[],
    @Body() dto: CreateVehicleDto,
  ) {
    // 1. File ảnh đại diện
    const imageFile = files.find((f) => f.fieldname === 'image');

    // 2. File ảnh thông số kỹ thuật
    const specFiles = files.filter((f) => f.fieldname === 'spec_images');

    // 3. Upload Cloudinary
    if (imageFile) {
      dto.image_url = await this.cloudinaryService.uploadImage(imageFile);
    }

    if (specFiles.length > 0) {
      dto.spec_image_urls =
        await this.cloudinaryService.uploadMultiple(specFiles);
    }

    return this.vehiclesService.create(dto);
  }

  // Upload nhiều ảnh specs
  @Post('upload-specs')
  @UseInterceptors(FilesInterceptor('spec_images'))
  async uploadSpecImages(@UploadedFiles() files: Express.Multer.File[]) {
    return { urls: await this.cloudinaryService.uploadMultiple(files) };
  }

  // ============================
  // GET ALL
  // ============================
  @Get()
  findAll() {
    return this.vehiclesService.findAll();
  }

  // ============================
  // GET ONE (INT)
  // ============================
  @Get(':id')
  findOne(@Param('id', ParseIntPipe) id: number) {
    return this.vehiclesService.findOne(id);
  }

  // ============================
  // UPDATE (INT)
  // ============================
  @Patch(':id')
  update(@Param('id', ParseIntPipe) id: number, @Body() dto: UpdateVehicleDto) {
    return this.vehiclesService.update(id, dto);
  }

  // ============================
  // DELETE (INT)
  // ============================
  @Delete(':id')
  remove(@Param('id', ParseIntPipe) id: number) {
    return this.vehiclesService.delete(id);
  }

  // ============================
  // UPDATE IMAGES
  // ============================
  @Patch(':id/images')
  updateImages(
    @Param('id', ParseIntPipe) id: number,
    @Body()
    body: { image_url: string; spec_image_urls?: string[] },
  ) {
    return this.vehiclesService.updateImages(
      id,
      body.image_url,
      body.spec_image_urls,
    );
  }
}
