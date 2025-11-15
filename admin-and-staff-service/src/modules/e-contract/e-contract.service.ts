// e-contracts/e-contract.service.ts
import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { EContract, SignatureStatus } from './e-contract.entity';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';
import { CreateEContractDto, UpdateEContractDto } from './e-contract.dto';
import { HttpUserService } from '../common/http-user.service';

@Injectable()
export class EContractService {
  constructor(
    @InjectRepository(EContract)
    private readonly contractRepo: Repository<EContract>,

    @InjectRepository(OwnershipGroup)
    private readonly groupRepo: Repository<OwnershipGroup>,

    private readonly httpUserService: HttpUserService, // gọi User Service
  ) {}

  async create(dto: CreateEContractDto): Promise<EContract> {
    // 1. Kiểm tra nhóm sở hữu tồn tạ
    const group = await this.groupRepo.findOne({
      where: { group_id: dto.ownership_group_id },
    });
    if (!group) {
      throw new NotFoundException('Nhóm sở hữu không tồn tại');
    }
    // 4️⃣ Gọi User Service xác thực user_id
    const user = await this.httpUserService.getUserById(dto.user_id);
    if (!user) throw new NotFoundException('User not found in User Service');

    const contract = this.contractRepo.create({
      ownership_group_id: dto.ownership_group_id, // Bây giờ hợp lệ
      contract_url: dto.contract_url,
      user_id: dto.user_id,
      signature_status: dto.signature_status ?? SignatureStatus.PENDING, // Dùng enum
      ownership_group: group,
    });
    // 3. Lưu vào DB
    return await this.contractRepo.save(contract);
  }

  async findAll(): Promise<EContract[]> {
    return await this.contractRepo.find({
      relations: ['ownership_group'],
      order: { created_at: 'DESC' },
    });
  }

  async findOne(id: number): Promise<EContract> {
    const contract = await this.contractRepo.findOne({
      where: { contract_id: id },
      relations: ['ownership_group'],
    });
    if (!contract) {
      throw new NotFoundException('Hợp đồng không tồn tại');
    }
    return contract;
  }

  async update(id: number, dto: UpdateEContractDto): Promise<EContract> {
    const contract = await this.findOne(id);
    Object.assign(contract, dto);
    return await this.contractRepo.save(contract);
  }

  async remove(id: number): Promise<void> {
    const contract = await this.findOne(id);
    await this.contractRepo.remove(contract);
  }
}
