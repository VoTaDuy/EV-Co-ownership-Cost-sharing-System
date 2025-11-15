export class UserResponseDto {
  user_id: number;
  email: string;
  isVerified: boolean;
  isDeleted: boolean;
  createdAt: Date;
  role: string; // hoặc object nếu bạn muốn trả chi tiết role
  passwordResetToken?: string | null;
  resetTokenExpiry?: Date | null;
}
