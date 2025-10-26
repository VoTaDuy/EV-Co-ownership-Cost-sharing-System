import {
  Controller,
  Post,
  Body,
  UploadedFile,
  UploadedFiles,
  UseInterceptors,
} from '@nestjs/common';
import { FileInterceptor, FilesInterceptor } from '@nestjs/platform-express';
import { CloudinaryService } from '../cloudinary/cloudinary.service';
import { CreateVehicleDto } from '../vehicles/vehicles.dto';

@Controller('vehicles')
export class VehicleController {
  constructor(private readonly cloudinaryService: CloudinaryService) {}

  @Post('create')
  @UseInterceptors(FileInterceptor('image')) // upload 1 file
  async createVehicle(
    @Body() body: CreateVehicleDto,
    @UploadedFile() image?: Express.Multer.File,
  ) {
    if (image) {
      body.image_url = await this.cloudinaryService.uploadImage(image);
    }

    // Gọi service tạo vehicle trong DB, ví dụ:
    // return this.vehicleService.create(body);
    return body; // demo trả kết quả upload
  }

  @Post('upload-specs')
  @UseInterceptors(FilesInterceptor('spec_images')) // upload nhiều file
  async uploadSpecImages(
    @UploadedFiles() files: Express.Multer.File[],
  ): Promise<{ urls: string[] }> {
    const urls = await this.cloudinaryService.uploadMultiple(files);
    return { urls };
  }
}
