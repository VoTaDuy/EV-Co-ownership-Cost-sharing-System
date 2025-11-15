// e-contracts/e-contract.controller.ts
import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
  UseInterceptors,
  UploadedFiles,
  BadRequestException,
} from '@nestjs/common';
import { EContractService } from './e-contract.service';
import { CreateEContractDto, UpdateEContractDto } from './e-contract.dto';
import { CloudinaryService } from '../cloudinary/cloudinary.service';
import { AnyFilesInterceptor } from '@nestjs/platform-express';

@Controller('admin/e-contracts')
export class EContractController {
  constructor(
    private readonly service: EContractService,
    private readonly cloudinaryService: CloudinaryService,
  ) {}

  @Post()
  @UseInterceptors(AnyFilesInterceptor())
  async create(
    @UploadedFiles() files: Express.Multer.File[],
    @Body() dto: CreateEContractDto,
  ) {
    const pdfFile = files?.[0];
    if (!pdfFile) throw new BadRequestException('PDF file is required');

    const url = await this.cloudinaryService.uploadPdf(pdfFile);

    // Gán URL sau khi DTO đã được validate
    dto.contract_url = url;

    return this.service.create(dto);
  }

  @Get()
  findAll() {
    return this.service.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: number) {
    return this.service.findOne(id);
  }

  @Put(':id')
  update(@Param('id') id: number, @Body() dto: UpdateEContractDto) {
    return this.service.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: number) {
    return this.service.remove(id);
  }
}
