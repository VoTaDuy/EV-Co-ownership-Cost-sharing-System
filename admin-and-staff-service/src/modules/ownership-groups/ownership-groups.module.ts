import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { OwnershipGroupsService } from './ownership-groups.service';
import { OwnershipGroupsController } from './ownership-groups.controller';
import { OwnershipGroup } from './ownership-groups.entity';

@Module({
  imports: [TypeOrmModule.forFeature([OwnershipGroup])],
  controllers: [OwnershipGroupsController],
  providers: [OwnershipGroupsService],
  exports: [OwnershipGroupsService, TypeOrmModule], // ✅ Xuất ra nếu dùng ở module khác
})
export class OwnershipGroupsModule {}
