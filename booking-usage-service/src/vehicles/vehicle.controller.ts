// src/vehicles/vehicle.controller.ts
import { Controller, Get, Query } from '@nestjs/common';
import { VehicleService } from './vehicle.service';

@Controller('booking/vehicles')
export class VehicleController {
  constructor(private readonly vehicleService: VehicleService) {}

  @Get('timeline')
  async getTimeline(@Query('startDate') startDate: string) {
    if (!startDate) {
      return { error: 'Thiếu query parameter: startDate' };
    }
    const data = await this.vehicleService.getVehicleTimeline(startDate);
    return { startDate, vehicles: data };
  }
}
