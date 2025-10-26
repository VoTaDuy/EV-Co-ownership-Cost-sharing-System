import { Module } from '@nestjs/common';
import { CloudinaryConfig } from './cloudinary.config';
import { CloudinaryService } from './cloudinary.service';

@Module({
  providers: [CloudinaryService],
  exports: [CloudinaryService], // ✅ Export để module khác dùng được
})
export class CloudinaryModule {
  constructor() {
    CloudinaryConfig(); // cấu hình khi khởi tạo module
  }
}
