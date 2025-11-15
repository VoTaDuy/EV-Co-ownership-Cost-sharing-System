import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { ServiceTask } from './service-tasks.entity';
import {
  CreateServiceTaskDto,
  UpdateServiceTaskDto,
} from './service-tasks.dto';

@Injectable()
export class ServiceTasksService {
  constructor(
    @InjectRepository(ServiceTask)
    private readonly taskRepo: Repository<ServiceTask>,
  ) {}

  async create(data: CreateServiceTaskDto): Promise<ServiceTask> {
    const task = this.taskRepo.create(data);
    return await this.taskRepo.save(task);
  }

  async findAll(): Promise<ServiceTask[]> {
    return await this.taskRepo.find({ relations: ['vehicle'] });
  }

  async findOne(id: number): Promise<ServiceTask> {
    const task = await this.taskRepo.findOne({
      where: { task_id: id },
      relations: ['vehicle'],
    });
    if (!task) throw new NotFoundException('Service task not found');
    return task;
  }

  async update(id: number, data: UpdateServiceTaskDto): Promise<ServiceTask> {
    const task = await this.findOne(id);
    Object.assign(task, data);
    return await this.taskRepo.save(task);
  }

  async delete(id: number): Promise<void> {
    const result = await this.taskRepo.delete(id);
    if (result.affected === 0)
      throw new NotFoundException('Service task not found');
  }

  async updateStatus(id: number, status: ServiceTask['status']) {
    const task = await this.taskRepo.findOne({ where: { task_id: id } });
    if (!task) throw new NotFoundException(`Task ${id} not found`);
    task.status = status;
    task.updated_at = new Date();
    if (status === 'completed') task.completed_at = new Date();
    return this.taskRepo.save(task);
  }
}
