import { HttpModule } from '@nestjs/axios';
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { EContract } from './e-contract.entity';
import { EContractController } from './e-contract.controller';
import { EContractService } from './e-contract.service';
import { OwnershipGroup } from '../ownership-groups/ownership-groups.entity';
import { CloudinaryModule } from '../cloudinary/cloudinary.module';
import { HttpUserService } from '../common/http-user.service';

@Module({
  imports: [
    TypeOrmModule.forFeature([EContract, OwnershipGroup]),
    HttpModule,
    CloudinaryModule,
  ],
  controllers: [EContractController],
  providers: [EContractService, HttpUserService],
})
export class EContractModule {}
