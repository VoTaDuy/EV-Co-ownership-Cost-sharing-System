import { Controller, Post, Get, Param, Body } from '@nestjs/common';
import { DigitalSignatureService } from './digital-signature.service';
import { ApiTags, ApiOperation, ApiParam } from '@nestjs/swagger';
import { CreateSignatureDto } from './dto/create-signature.dto';
import { UserSignatureDto } from './dto/user-signature.dto';
import { IdSignatureDto } from './dto/id-signature.dto';

@ApiTags('digital-signature')
@Controller('booking/digital-signature')
export class DigitalSignatureController {
  constructor(private readonly signatureService: DigitalSignatureService) {}

  @Post()
  @ApiOperation({ summary: 'Tạo chữ ký số (check-in hoặc check-out)' })
  async createSignature(@Body() data: CreateSignatureDto) {
    return this.signatureService.createSignature(data);
  }

  @Get('get-all')
  @ApiOperation({ summary: 'Lấy danh sách tất cả chữ ký số' })
  async getAllSignatures() {
    return this.signatureService.getAllSignatures();
  }

  @Get('id/:id')
  @ApiOperation({ summary: 'Lấy chữ ký số theo ID' })
  @ApiParam({ name: 'id', description: 'ID của chữ ký số' })
  async getSignatureById(@Param() params: IdSignatureDto) {
    return this.signatureService.getSignatureById(params.id);
  }

  @Get('user/:user_id')
  @ApiOperation({ summary: 'Lấy danh sách chữ ký theo User ID' })
  @ApiParam({ name: 'user_id', description: 'ID của người dùng' })
  async getSignatureByUser(@Param() params: UserSignatureDto) {
    return this.signatureService.getSignaturesByUser(params.user_id);
  }
}
