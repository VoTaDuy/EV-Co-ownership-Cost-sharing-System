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

  /**
   * Tạo mới một vehicle
   */
  async create(data: CreateVehicleDto): Promise<Vehicle> {
    const vehicle = this.vehicleRepo.create({
      ...data,
      created_at: new Date(),
      updated_at: new Date(),
    });
    return await this.vehicleRepo.save(vehicle);
  }

  /**
   * Lấy tất cả vehicle
   */
  async findAll(): Promise<Vehicle[]> {
    return await this.vehicleRepo.find();
  }

  /**
   * Lấy 1 vehicle theo id
   */
  async findOne(id: string): Promise<Vehicle> {
    const vehicle = await this.vehicleRepo.findOne({
      where: { vehicle_id: id },
    });
    if (!vehicle) {
      throw new NotFoundException(`Vehicle with id ${id} not found`);
    }
    return vehicle;
  }

  /**
   * Cập nhật thông tin vehicle
   */
  async update(id: string, data: UpdateVehicleDto): Promise<Vehicle> {
    const vehicle = await this.findOne(id);
    Object.assign(vehicle, data, { updated_at: new Date() });
    return await this.vehicleRepo.save(vehicle);
  }

  /**
   * Xóa vehicle
   */
  async delete(id: string): Promise<boolean> {
    const result = await this.vehicleRepo.delete(id);
    if (result.affected === 0) {
      throw new NotFoundException(`Vehicle with id ${id} not found`);
    }
    return true;
  }

  /**
   * Cập nhật ảnh đại diện và ảnh thông số kỹ thuật
   */
  async updateImages(
    id: string,
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
