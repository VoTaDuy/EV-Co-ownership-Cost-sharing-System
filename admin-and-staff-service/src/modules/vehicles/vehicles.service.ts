import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Vehicle } from './vehicles.entity';
import { CreateVehicleDto, UpdateVehicleDto } from './vehicles.dto';

@Injectable()
export class VehiclesService {
  constructor(
    @InjectRepository(Vehicle)
    private readonly vehicleRepo: Repository<Vehicle>,
  ) {}

  async create(data: CreateVehicleDto): Promise<Vehicle> {
    const vehicle = this.vehicleRepo.create(data);
    return await this.vehicleRepo.save(vehicle);
  }

  async findAll(): Promise<Vehicle[]> {
    return await this.vehicleRepo.find();
  }

  async findOne(id: string): Promise<Vehicle> {
    const vehicle = await this.vehicleRepo.findOne({
      where: { vehicle_id: id },
    });
    if (!vehicle) throw new NotFoundException('Vehicle not found');
    return vehicle;
  }

  async update(id: string, data: UpdateVehicleDto): Promise<Vehicle> {
    const vehicle = await this.findOne(id);
    Object.assign(vehicle, data);
    return await this.vehicleRepo.save(vehicle);
  }

  async delete(id: string): Promise<void> {
    const result = await this.vehicleRepo.delete(id);
    if (result.affected === 0) throw new NotFoundException('Vehicle not found');
  }

  async updateImages(
    id: string,
    image_url: string,
    spec_image_urls?: string[],
  ) {
    await this.vehicleRepo.update(id, { image_url, spec_image_urls });
    return this.findOne(id);
  }
}
