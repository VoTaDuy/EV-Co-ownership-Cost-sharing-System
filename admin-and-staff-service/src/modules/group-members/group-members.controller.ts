import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
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
  findOne(@Param('id') id: string) {
    return this.service.findOne(id);
  }

  @Get('group/:group_id')
  findMemberByGroupId(@Param('group_id') id: string) {
    return this.service.findMember(id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() dto: UpdateGroupMemberDto) {
    return this.service.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.service.remove(id);
  }

  // 🔥 Route thêm member vào group
  @Post('/:group_id/add')
  addMemberToGroup(
    @Param('group_id') group_id: string,
    @Body() dto: AddGroupMemberDto,
  ) {
    return this.service.addMemberToGroup(group_id, dto);
  }
}
