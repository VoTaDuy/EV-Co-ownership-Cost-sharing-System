// src/vehicles/vehicle.entity.ts
import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity({ name: 'vehicles' })
export class Vehicle {
  @PrimaryGeneratedColumn()
  vehicle_id: number;

  @Column()
  vehicle_name: string;

  @Column()
  license_plate: string;

  @Column({ nullable: true })
  description?: string;

  @Column({ nullable: true })
  avatar_url?: string;

  @Column({ type: 'json', nullable: true })
  images?: string[];

  @Column({ type: 'tinyint', default: 1 })
  is_available: boolean;
}
