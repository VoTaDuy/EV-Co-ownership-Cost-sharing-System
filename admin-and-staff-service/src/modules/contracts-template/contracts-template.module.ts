import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ContractsTemplate } from './contracts-template.entity';
import { ContractsTemplateService } from './contracts-template.service';
import { ContractsTemplateController } from './contracts-template.controller';

@Module({
  imports: [TypeOrmModule.forFeature([ContractsTemplate])],
  providers: [ContractsTemplateService],
  controllers: [ContractsTemplateController],
})
export class ContractsTemplateModule {}
