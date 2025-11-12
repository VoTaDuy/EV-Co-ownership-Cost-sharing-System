import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { HttpModule } from '@nestjs/axios'; // cần để HttpUserService dùng HttpService
import { GroupMember } from './group-members.entity';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';
import { GroupMembersService } from './group-members.service';
import { GroupMembersController } from './group-members.controller';
import { HttpUserService } from '../common/http-user.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([GroupMember, OwnershipGroup]),
    HttpModule, // ✅ import module để HttpUserService dùng HttpService
  ],
  controllers: [GroupMembersController],
  providers: [GroupMembersService, HttpUserService], // ✅ service phải ở providers
  exports: [TypeOrmModule, GroupMembersService],
})
export class GroupMembersModule {}
