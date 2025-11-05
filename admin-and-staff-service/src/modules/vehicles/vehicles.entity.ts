import { Entity, PrimaryGeneratedColumn, Column, BeforeInsert } from 'typeorm';
import { OneToMany } from 'typeorm';
import { v4 as uuidv4 } from 'uuid';
import { Report } from '../report/report.entity';

@Entity('vehicles')
export class Vehicle {
  @PrimaryGeneratedColumn('uuid')
  vehicle_id: string;

  @Column()
  vehicle_name: string;

  @Column({ unique: true })
  license_plate: string;

  @Column({ nullable: true })
  description: string;

  @Column({ nullable: true })
  image_url: string; // Ảnh đại diện của xe

  @Column('json', { nullable: true })
  spec_image_urls: string[]; // Danh sách ảnh thông số kỹ thuật

  @Column({ default: true })
  is_active: boolean;

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
  created_at: Date;

  @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
  updated_at: Date;

  @BeforeInsert()
  setDefaultValues() {
    if (!this.vehicle_id) {
      this.vehicle_id = uuidv4();
    }
    this.created_at = new Date();
    this.updated_at = new Date();
  }

  @OneToMany(() => Report, (report) => report.vehicle)
  reports: Report[];
}
