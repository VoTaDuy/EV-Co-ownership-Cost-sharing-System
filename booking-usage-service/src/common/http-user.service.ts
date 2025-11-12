import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class HttpUserService {
  private readonly userServiceUrl: string;

  constructor(private readonly httpService: HttpService) {
    // Lấy từ biến môi trường, fallback mặc định
    this.userServiceUrl =
      process.env.USER_SERVICE_URL || 'http://localhost:8080/user';
  }

  /**
   * Lấy thông tin user từ User Service
   * @param user_id UUID của user
   */
  async getUserById(user_id: string) {
    try {
      const url = `${this.userServiceUrl}/users/${user_id}`;
      const response = await firstValueFrom(this.httpService.get(url));
      return response.data;
    } catch (error: any) {
      console.error(
        'User Service fetch error:',
        error.response?.status,
        error.response?.data || error.message,
      );
      throw new HttpException(
        'Cannot fetch user from User Service',
        HttpStatus.NOT_FOUND,
      );
    }
  }
}