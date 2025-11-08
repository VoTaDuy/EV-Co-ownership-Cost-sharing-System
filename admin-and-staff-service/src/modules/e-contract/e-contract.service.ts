/* eslint-disable prettier/prettier */
import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { EContact } from './e-contract.entity';
import { GroupMember } from '../group-members/group-members.entity';
import { CreateEContactDto, UpdateEContactDto } from './e-contract.dto';

@Injectable()
export class EContactsService {
  constructor(
    @InjectRepository(EContact)
    private readonly contactRepo: Repository<EContact>,
    @InjectRepository(GroupMember)
    private readonly memberRepo: Repository<GroupMember>,
  ) {}

  async create(dto: CreateEContactDto): Promise<EContact> {
    const member = await this.memberRepo.findOne({ where: { member_id: dto.member_id } });
    if (!member) throw new NotFoundException('Group member not found');

    const contact = this.contactRepo.create({
      ...dto,
      member,
    });
    return await this.contactRepo.save(contact);
  }

  async findAll(): Promise<EContact[]> {
    return await this.contactRepo.find({ relations: ['member'] });
  }

  async findOne(id: string): Promise<EContact> {
    const contact = await this.contactRepo.findOne({ where: { contract_id: id }, relations: ['member'] });
    if (!contact) throw new NotFoundException('E-Contact not found');
    return contact;
  }

  async update(id: string, dto: UpdateEContactDto): Promise<EContact> {
    const contact = await this.findOne(id);
    Object.assign(contact, dto);
    return await this.contactRepo.save(contact);
  }

  async remove(id: string): Promise<void> {
    const contact = await this.findOne(id);
    await this.contactRepo.remove(contact);
  }
}
