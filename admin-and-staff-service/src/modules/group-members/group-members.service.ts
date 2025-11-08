import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { GroupMember } from './group-members.entity';
import {
  CreateGroupMemberDto,
  UpdateGroupMemberDto,
} from './group-members.dto';

@Injectable()
export class GroupMembersService {
  constructor(
    @InjectRepository(GroupMember)
    private readonly memberRepo: Repository<GroupMember>,
  ) {}

  async create(dto: CreateGroupMemberDto): Promise<GroupMember> {
    const member = this.memberRepo.create(dto);
    return await this.memberRepo.save(member);
  }

  async findAll(): Promise<GroupMember[]> {
    return await this.memberRepo.find({ relations: ['contacts'] });
  }

  async findOne(id: string): Promise<GroupMember> {
    const member = await this.memberRepo.findOne({
      where: { member_id: id },
      relations: ['contacts'],
    });
    if (!member) throw new NotFoundException('Group member not found');
    return member;
  }

  async update(id: string, dto: UpdateGroupMemberDto): Promise<GroupMember> {
    const member = await this.findOne(id);
    Object.assign(member, dto);
    return await this.memberRepo.save(member);
  }

  async remove(id: string): Promise<void> {
    const member = await this.findOne(id);
    await this.memberRepo.remove(member);
  }
}
