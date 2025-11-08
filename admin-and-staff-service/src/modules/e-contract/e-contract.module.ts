import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { GroupMember } from '../group-members/group-members.entity';
import { EContactsController } from './e-contract.controller';
import { EContact } from './e-contract.entity';
import { EContactsService } from './e-contract.service';

@Module({
  imports: [TypeOrmModule.forFeature([EContact, GroupMember])],
  controllers: [EContactsController],
  providers: [EContactsService],
})
export class EContactsModule {}
