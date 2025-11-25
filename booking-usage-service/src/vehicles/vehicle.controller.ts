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
}
