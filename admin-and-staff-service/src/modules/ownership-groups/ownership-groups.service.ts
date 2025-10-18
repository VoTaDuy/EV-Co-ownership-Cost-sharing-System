import {
  BadRequestException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { OwnershipGroup } from './ownership-groups.entity';
@Injectable()
export class OwnershipGroupsService {
  constructor(
    @InjectRepository(OwnershipGroup)
    private readonly groupRepo: Repository<OwnershipGroup>,
  ) {}

  // 🟢 Tạo mới group — mỗi xe chỉ được thuộc 1 nhóm
  async create(data: Partial<OwnershipGroup>): Promise<OwnershipGroup> {
    // 1️⃣ Kiểm tra xem xe đã thuộc group nào chưa
    const existing = await this.groupRepo.findOne({
      where: { vehicle_id: data.vehicle_id },
    });

    if (existing) {
      throw new BadRequestException('Xe này đã thuộc về một nhóm khác.');
    }
    const group = this.groupRepo.create(data);
    return await this.groupRepo.save(group);
  }

  async findAll(): Promise<OwnershipGroup[]> {
    return await this.groupRepo.find({ relations: ['vehicle'] });
  }

  async findOne(id: string): Promise<OwnershipGroup> {
    const group = await this.groupRepo.findOne({
      where: { group_id: id },
      relations: ['vehicle'],
    });
    if (!group) throw new NotFoundException('Ownership group not found');
    return group;
  }

  async update(
    id: string,
    data: Partial<OwnershipGroup>,
  ): Promise<OwnershipGroup> {
    const group = await this.findOne(id);
    Object.assign(group, data);
    return await this.groupRepo.save(group);
  }

  async delete(id: string): Promise<void> {
    const result = await this.groupRepo.delete(id);
    if (result.affected === 0) {
      throw new NotFoundException('Ownership group not found');
    }
  }
}
