import { Injectable } from '@nestjs/common';
import { DigitalSignatureRepository } from './digital-signature.repository';
import { DigitalSignature } from './digital-signature.entity';

@Injectable()
export class DigitalSignatureService {
  constructor(private readonly signatureRepo: DigitalSignatureRepository) {}

  // Tạo chữ ký số
  async createSignature(data: Partial<DigitalSignature>): Promise<DigitalSignature> {
    return this.signatureRepo.createSignature(data);
  }

  // Lấy tất cả chữ ký
  async getAllSignatures(): Promise<DigitalSignature[]> {
    return this.signatureRepo.findAll();
  }

  // Lấy chữ ký theo ID
  async getSignatureById(id: string): Promise<DigitalSignature | null> {
    return this.signatureRepo.findById(id);
  }

  // Lấy chữ ký theo user_id
  async getSignaturesByUser(user_id: string): Promise<DigitalSignature[]> {
    return this.signatureRepo.findByUser(user_id);
  }
}
