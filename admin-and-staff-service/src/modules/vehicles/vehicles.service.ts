import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Vehicle } from './vehicles.entity';

@Injectable()
export class VehiclesService {
  constructor(
    @InjectRepository(Vehicle)
    private readonly vehicleRepo: Repository<Vehicle>,
  ) {}

  findAll() {
    return this.vehicleRepo.find();
  }

  findOne(id: string) {
    return this.vehicleRepo.findOne({ where: { vehicle_id: id } });
  }

  create(data: Partial<Vehicle>) {
    const vehicle = this.vehicleRepo.create(data);
    return this.vehicleRepo.save(vehicle);
  }

  async update(id: string, data: Partial<Vehicle>) {
    await this.vehicleRepo.update(id, data);
    return this.findOne(id);
  }

  async updateImages(
    id: string,
    image_url: string,
    spec_image_urls?: string[],
  ) {
    await this.vehicleRepo.update(id, { image_url, spec_image_urls });
    return this.findOne(id);
  }

  async remove(id: string) {
    await this.vehicleRepo.delete(id);
    return { deleted: true };
  }
}
