import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class HttpAdminService {
  private readonly adminServiceUrl: string;

  constructor(private readonly httpService: HttpService) {
    this.adminServiceUrl =
      process.env.ADMIN_SERVICE_URL || 'http://localhost:8081/admin';
  }

  /**
   * Lấy thông tin vehicle từ Admin Service
   * @param vehicle_id UUID của vehicle
   */
  async getVehicleById(vehicle_id: string) {
    try {
      const url = `${this.adminServiceUrl}/vehicles/${vehicle_id}`;
      const response = await firstValueFrom(this.httpService.get(url));
      return response.data;
    } catch (error: any) {
      console.error(
        'Admin Service fetch error:',
        error.response?.status,
        error.response?.data || error.message,
      );
      throw new HttpException(
        'Cannot fetch vehicle from Admin Service',
        HttpStatus.NOT_FOUND,
      );
    }
  }
}
