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
    const vehicle = this.vehicleRepo.create({
      ...data,
      created_at: new Date(),
      updated_at: new Date(),
    });
    return await this.vehicleRepo.save(vehicle);
  }

  async findAll(): Promise<Vehicle[]> {
    return await this.vehicleRepo.find();
  }

  async findOne(id: number): Promise<Vehicle> {
    const vehicle = await this.vehicleRepo.findOne({
      where: { vehicle_id: id },
    });
    if (!vehicle) {
      throw new NotFoundException(`Vehicle with id ${id} not found`);
    }
    return vehicle;
  }

  async update(id: number, data: UpdateVehicleDto): Promise<Vehicle> {
    const vehicle = await this.findOne(id);
    Object.assign(vehicle, data, { updated_at: new Date() });
    return await this.vehicleRepo.save(vehicle);
  }

  async delete(id: number): Promise<boolean> {
    const result = await this.vehicleRepo.delete(id);
    if (result.affected === 0) {
      throw new NotFoundException(`Vehicle with id ${id} not found`);
    }
    return true;
  }

  async updateImages(
    id: number,
    image_url?: string,
    spec_image_urls?: string[],
  ): Promise<Vehicle> {
    const vehicle = await this.findOne(id);

    if (image_url) vehicle.image_url = image_url;
    if (spec_image_urls) vehicle.spec_image_urls = spec_image_urls;

    vehicle.updated_at = new Date();
    return await this.vehicleRepo.save(vehicle);
  }
}
