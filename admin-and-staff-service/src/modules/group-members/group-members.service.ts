/* eslint-disable @typescript-eslint/no-unsafe-assignment */
import {
  ConflictException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { GroupMember } from './group-members.entity';
import {
  AddGroupMemberDto,
  CreateGroupMemberDto,
  UpdateGroupMemberDto,
} from './group-members.dto';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';
import { HttpUserService } from '../common/http-user.service';

@Injectable()
export class GroupMembersService {
  constructor(
    @InjectRepository(GroupMember)
    private readonly memberRepo: Repository<GroupMember>,

    @InjectRepository(OwnershipGroup)
    private readonly groupRepo: Repository<OwnershipGroup>,

    private readonly httpUserService: HttpUserService, // gọi User Service
  ) {}

  async create(dto: CreateGroupMemberDto): Promise<GroupMember> {
    const member = this.memberRepo.create(dto);
    return await this.memberRepo.save(member);
  }

  async findAll(): Promise<GroupMember[]> {
    return await this.memberRepo.find({ relations: ['contacts'] });
  }

  async findOne(id: number): Promise<GroupMember> {
    const member = await this.memberRepo.findOne({
      where: { member_id: id },
      relations: ['contacts'],
    });
    if (!member) throw new NotFoundException('Group member not found');
    return member;
  }

  async findMember(id: number): Promise<GroupMember[]> {
    const member = await this.memberRepo.find({
      where: { group_id: id },
      relations: ['contacts'],
    });
    if (!member) throw new NotFoundException('Group member not found');
    return member;
  }

  async update(id: number, dto: UpdateGroupMemberDto): Promise<GroupMember> {
    const member = await this.findOne(id);
    Object.assign(member, dto);
    return await this.memberRepo.save(member);
  }

  async remove(id: number): Promise<void> {
    const member = await this.findOne(id);
    await this.memberRepo.remove(member);
  }

  async addMemberToGroup(group_id: number, dto: AddGroupMemberDto) {
    // 1️⃣ Kiểm tra group tồn tại
    const group = await this.groupRepo.findOne({
      where: { group_id },
      relations: ['members'], // load members hiện tại
    });
    if (!group) throw new NotFoundException('Ownership group not found');

    // 2️⃣ Kiểm tra user đã là member chưa
    const existing = await this.memberRepo.findOne({
      where: { group_id, user_id: dto.user_id },
    });
    if (existing) throw new ConflictException('User already in this group');

    // 3️⃣ Kiểm tra ownership_ratio hợp lệ
    const ratio = dto.ownership_ratio ?? 0;

    const totalRatio = group.members.reduce(
      (sum, m) => sum + Number(m.ownership_ratio),
      0,
    );
    if (totalRatio + ratio > 100)
      throw new ConflictException('Total ownership ratio cannot exceed 100%');

    // 4️⃣ Gọi User Service xác thực user_id
    const user = await this.httpUserService.getUserById(dto.user_id);
    if (!user) throw new NotFoundException('User not found in User Service');

    // 5️⃣ Tạo GroupMember mới
    const member = this.memberRepo.create({
      group_id,
      user_id: dto.user_id,
      group_role: dto.group_role || 'Co-owner',
      ownership_ratio: ratio,
    });

    // 6️⃣ Lưu vào DB
    await this.memberRepo.save(member);

    // 7️⃣ Trả về thông tin member + info user
    return {
      ...member,
      user,
    };
  }
}
