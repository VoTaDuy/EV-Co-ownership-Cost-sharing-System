export class CreateGroupMemberDto {
  user_id: string;
  group_id: string;
  group_role?: string;
  ownership_ratio?: number;
}

export class UpdateGroupMemberDto {
  group_role?: string;
  ownership_ratio?: number;
}
