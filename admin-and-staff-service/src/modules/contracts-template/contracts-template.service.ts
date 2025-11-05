import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { ContractsTemplate } from './contracts-template.entity';

@Injectable()
export class ContractsTemplateService {
  constructor(
    @InjectRepository(ContractsTemplate)
    private readonly contractsRepository: Repository<ContractsTemplate>,
  ) {}

  async findAll(): Promise<ContractsTemplate[]> {
    return this.contractsRepository.find({ relations: ['group'] });
  }

  async findOne(id: string): Promise<ContractsTemplate> {
    const contract = await this.contractsRepository.findOne({
      where: { contract_id: id },
      relations: ['group'],
    });
    if (!contract) {
      throw new NotFoundException(`Contract ${id} not found`);
    }
    return contract;
  }

  async create(data: Partial<ContractsTemplate>): Promise<ContractsTemplate> {
    const newContract = this.contractsRepository.create(data);
    return this.contractsRepository.save(newContract);
  }

  async update(
    id: string,
    data: Partial<ContractsTemplate>,
  ): Promise<ContractsTemplate> {
    const contract = await this.findOne(id);
    Object.assign(contract, data);
    return this.contractsRepository.save(contract);
  }

  async remove(id: string): Promise<void> {
    const contract = await this.findOne(id);
    await this.contractsRepository.remove(contract);
  }
}
