import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { ServiceTask } from './service-tasks.entity';

@Injectable()
export class ServiceTasksService {
  constructor(
    @InjectRepository(ServiceTask)
    private readonly taskRepo: Repository<ServiceTask>,
  ) {}

  findAll() {
    return this.taskRepo.find({ relations: ['vehicle'] });
  }

  findOne(id: string) {
    return this.taskRepo.findOne({
      where: { task_id: id },
      relations: ['vehicle'],
    });
  }

  async create(data: Partial<ServiceTask>) {
    const task = this.taskRepo.create(data);
    return this.taskRepo.save(task);
  }

  async update(id: string, data: Partial<ServiceTask>) {
    const task = await this.taskRepo.findOne({ where: { task_id: id } });
    if (!task) throw new NotFoundException(`Task ${id} not found`);
    await this.taskRepo.update(id, { ...data, updated_at: new Date() });
    return this.findOne(id);
  }

  async updateStatus(id: string, status: ServiceTask['status']) {
    const task = await this.taskRepo.findOne({ where: { task_id: id } });
    if (!task) throw new NotFoundException(`Task ${id} not found`);
    task.status = status;
    task.updated_at = new Date();
    if (status === 'completed') task.completed_at = new Date();
    return this.taskRepo.save(task);
  }

  async remove(id: string) {
    await this.taskRepo.delete(id);
    return { deleted: true };
  }
}
