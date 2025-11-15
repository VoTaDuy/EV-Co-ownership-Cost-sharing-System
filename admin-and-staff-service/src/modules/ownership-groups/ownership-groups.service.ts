import {
  BadRequestException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { OwnershipGroup } from './ownership-groups.entity';
import {
  CreateOwnershipGroupDto,
  UpdateOwnershipGroupDto,
} from './ownership-groups.dto';
@Injectable()
export class OwnershipGroupsService {
  constructor(
    @InjectRepository(OwnershipGroup)
    private readonly groupRepo: Repository<OwnershipGroup>,
  ) {}

  // 🟢 Tạo mới group — mỗi xe chỉ được thuộc 1 nhóm
  async create(dto: CreateOwnershipGroupDto): Promise<OwnershipGroup> {
    // 1️⃣ Kiểm tra xem xe đã thuộc group nào chưa
    const existing = await this.groupRepo.findOne({
      where: { vehicle_id: dto.vehicle_id },
    });

    if (existing) {
      throw new BadRequestException('Xe này đã thuộc về một nhóm khác.');
    }
    const group = this.groupRepo.create(dto);
    return await this.groupRepo.save(group);
  }

  async findAll(): Promise<OwnershipGroup[]> {
    return await this.groupRepo.find({ relations: ['vehicle'] });
  }

  async findOne(id: number): Promise<OwnershipGroup> {
    const group = await this.groupRepo.findOne({
      where: { group_id: id },
      relations: ['vehicle'],
    });
    if (!group) throw new NotFoundException('Ownership group not found');
    return group;
  }

  async update(
    id: number,
    dto: UpdateOwnershipGroupDto,
  ): Promise<OwnershipGroup> {
    const group = await this.findOne(id);
    Object.assign(group, dto);
    return await this.groupRepo.save(group);
  }

  async delete(id: number): Promise<void> {
    const result = await this.groupRepo.delete(id);
    if (result.affected === 0) {
      throw new NotFoundException('Ownership group not found');
    }
  }
}
