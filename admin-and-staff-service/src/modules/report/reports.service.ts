import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Report } from './report.entity';
import { CreateReportDto, UpdateReportDto } from './reports.dto';

@Injectable()
export class ReportService {
  constructor(
    @InjectRepository(Report)
    private reportRepo: Repository<Report>,
  ) {}

  async create(dto: CreateReportDto): Promise<Report> {
    const report = this.reportRepo.create(dto);
    return this.reportRepo.save(report);
  }

  async findAll(): Promise<Report[]> {
    return this.reportRepo.find({ relations: ['vehicle'] });
  }

  async findOne(id: string): Promise<Report> {
    const report = await this.reportRepo.findOne({
      where: { report_id: id },
      relations: ['vehicle'],
    });
    if (!report) throw new NotFoundException('Report not found');
    return report;
  }

  async update(id: string, dto: UpdateReportDto): Promise<Report> {
    const report = await this.findOne(id);
    Object.assign(report, dto);
    return this.reportRepo.save(report);
  }

  async remove(id: string): Promise<void> {
    const report = await this.findOne(id);
    await this.reportRepo.remove(report);
  }
}
