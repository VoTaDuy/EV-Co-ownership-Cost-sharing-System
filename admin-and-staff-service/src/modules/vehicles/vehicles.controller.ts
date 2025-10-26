import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Body,
  Param,
  Patch,
  UseInterceptors,
  UploadedFile,
  UploadedFiles,
} from '@nestjs/common';
import { FileInterceptor, FilesInterceptor } from '@nestjs/platform-express';
import { VehiclesService } from './vehicles.service';
import { CloudinaryService } from '../cloudinary/cloudinary.service';
import { CreateVehicleDto, UpdateVehicleDto } from './vehicles.dto';

@Controller('vehicles')
export class VehiclesController {
  constructor(
    private readonly vehiclesService: VehiclesService,
    private readonly cloudinaryService: CloudinaryService,
  ) {}

  @Post()
  @UseInterceptors(FileInterceptor('image'))
  async createVehicle(
    @UploadedFile() image: Express.Multer.File,
    @Body() dto: CreateVehicleDto,
  ) {
    if (image) {
      const uploadedUrl = await this.cloudinaryService.uploadImage(image);
      dto.image_url = uploadedUrl;
    }

    return this.vehiclesService.create(dto);
  }

  // ✅ Upload nhiều ảnh (ảnh thông số kỹ thuật)
  @Post('upload-specs')
  @UseInterceptors(FilesInterceptor('spec_images'))
  async uploadSpecImages(
    @UploadedFiles() files: Express.Multer.File[],
  ): Promise<{ urls: string[] }> {
    const urls = await this.cloudinaryService.uploadMultiple(files);
    return { urls };
  }

  // ✅ Lấy danh sách xe
  @Get()
  findAll() {
    return this.vehiclesService.findAll();
  }

  // ✅ Lấy 1 xe theo ID
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.vehiclesService.findOne(id);
  }

  // ✅ Cập nhật xe
  @Patch(':id')
  update(@Param('id') id: string, @Body() dto: UpdateVehicleDto) {
    return this.vehiclesService.update(id, dto);
  }

  // ✅ Xoá xe
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.vehiclesService.delete(id);
  }

  // ✅ Cập nhật ảnh xe (nếu cần tách riêng)
  @Put(':id/images')
  updateImages(
    @Param('id') id: string,
    @Body() body: { image_url: string; spec_image_urls?: string[] },
  ) {
    const { image_url, spec_image_urls } = body;
    return this.vehiclesService.updateImages(id, image_url, spec_image_urls);
  }
}
