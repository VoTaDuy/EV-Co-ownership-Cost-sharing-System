import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
  Query,
} from '@nestjs/common';
import {
  CreateGroupMemberDto,
  UpdateGroupMemberDto,
  AddGroupMemberDto,
} from './group-members.dto';
import { GroupMembersService } from './group-members.service';

@Controller('admin/group-members')
export class GroupMembersController {
  constructor(private readonly service: GroupMembersService) {}

  @Post()
  create(@Body() dto: CreateGroupMemberDto) {
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

  @Get('group/:group_id')
  findMemberByGroupId(@Param('group_id') id: number) {
    return this.service.findMember(id);
  }

  @Put(':id')
  update(@Param('id') id: number, @Body() dto: UpdateGroupMemberDto) {
    return this.service.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: number) {
    return this.service.remove(id);
  }

  // 🔥 Route thêm member vào group
  @Post('/:group_id/add')
  addMemberToGroup(
    @Param('group_id') group_id: number,
    @Body() dto: AddGroupMemberDto,
  ) {
    return this.service.addMemberToGroup(group_id, dto);
  }

  // 🔥 Xóa thành viên khỏi nhóm (dựa trên group_id và user_id)
  @Delete('/:group_id/remove/:user_id')
  removeMemberFromGroup(
    @Param('group_id') group_id: number,
    @Param('user_id') user_id: number,
  ) {
    return this.service.removeMemberFromGroup(group_id, user_id);
  }
  @Get('/:groupId/cost')
  calculateCost(
    @Param('groupId') groupId: number,
    @Query('total') total: number,
  ) {
    return this.service.calculateCostByOwnership(
      Number(groupId),
      Number(total),
    );
  }
}
