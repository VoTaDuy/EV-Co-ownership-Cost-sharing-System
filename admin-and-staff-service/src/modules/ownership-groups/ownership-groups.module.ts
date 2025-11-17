import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { OwnershipGroupsService } from './ownership-groups.service';
import { OwnershipGroupsController } from './ownership-groups.controller';
import { OwnershipGroup } from './ownership-groups.entity';
import { HttpModule } from '@nestjs/axios';
import { GroupMembersModule } from '../group-members/group-members.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([OwnershipGroup]),
    HttpModule,
    GroupMembersModule,
  ],
  controllers: [OwnershipGroupsController],
  providers: [OwnershipGroupsService],
  exports: [OwnershipGroupsService, TypeOrmModule], // ✅ Xuất ra nếu dùng ở module khác
})
export class OwnershipGroupsModule {}
