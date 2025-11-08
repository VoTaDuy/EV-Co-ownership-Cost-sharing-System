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
} from './group-members.dto';
import { GroupMembersService } from './group-members.service';

@Controller('group-members')
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

  @Put(':id')
  update(@Param('id') id: string, @Body() dto: UpdateGroupMemberDto) {
    return this.service.update(id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.service.remove(id);
  }
}
