import {ApiProperty} from '@nestjs/swagger';

export class CreateBookingDto {
    @ApiProperty({
        description: 'ID của chủ xe (Co-owner)',
        example: 'user-1234',
    })
    user_id: string;
    
    @ApiProperty({
        description: 'ID của xe được đặt',
        example: 'vehicle-5678',
    })
    vehicle_id: string;

    @ApiProperty({
        description: 'Ngày đặt xe',
        example: '2024-07-15',
    })
    booking_date: Date;

    @ApiProperty({
        description: 'Thời gian bắt đầu đặt xe (định dạng HH:MM)',
        example: '09:00',
    })
    check_in_time: string;

    @ApiProperty({
        description: 'Thời gian kết thúc đặt xe (định dạng HH:MM)',
        example: '17:00',
    })
    check_out_time: string;
}