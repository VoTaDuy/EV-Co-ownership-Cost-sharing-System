export class CreateEContactDto {
  member_id: string;
  vehicle_id: string;
  template_id: string;
  signature_status?: string;
  signed_at?: Date;
}

export class UpdateEContactDto {
  signature_status?: string;
  signed_at?: Date;
}
