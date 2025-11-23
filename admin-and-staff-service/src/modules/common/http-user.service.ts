/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-unsafe-return */
import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { firstValueFrom } from 'rxjs';
import { UserResponseDto } from './user.dto';

@Injectable()
export class HttpUserService {
  constructor(private readonly httpService: HttpService) {}

  /**
   * Lấy thông tin user từ User Service
   * @param user_id
   */
  async getUserById(user_id: number): Promise<UserResponseDto | null> {
    try {
      const url = `${process.env.USER_SERVICE_URL}/users/${user_id}`;
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
  async getProfileByUserId(user_id: number): Promise<UserResponseDto | null> {
    try {
      const url = `${process.env.USER_SERVICE_URL}/profiles/${user_id}`;
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
