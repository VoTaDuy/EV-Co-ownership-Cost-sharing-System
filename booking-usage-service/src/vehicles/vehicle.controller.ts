// src/vehicles/vehicle.controller.ts
import { Controller, Get, Query , Param} from '@nestjs/common';
import { VehicleService } from './vehicle.service';

@Controller('booking/vehicles')
export class VehicleController {
  constructor(private readonly vehicleService: VehicleService) {}

  @Get(':vehicleId/timeline')
  async getTimeline(
    @Param('vehicleId') vehicleId: number,
    @Query('startDate') startDate: string
  ) {
    return this.vehicleService.getVehicleGroupTimeline(vehicleId, startDate);
  }

  @Get('status/today')
  async getVehiclesStatusToday(@Query('date') date?: string) {
    const targetDate = date || new Date().toISOString().split('T')[0]; // YYYY-MM-DD
    return this.vehicleService.getVehiclesStatusSummaryByDate(targetDate);
  }
}
