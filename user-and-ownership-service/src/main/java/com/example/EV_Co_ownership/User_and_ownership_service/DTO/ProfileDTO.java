    package com.example.EV_Co_ownership.User_and_ownership_service.DTO;

    import java.util.UUID;

    public class ProfileDTO {

        private UUID profiles_id;
        private UUID user_id;
        private String full_name;
        private String phone_number;
        private String address;
        private String driver_license_number;
        private String driver_license_expiry;
        private String license_image_url;

        public UUID getProfiles_id() {
            return profiles_id;
        }

        public void setProfiles_id(UUID profiles_id) {
            this.profiles_id = profiles_id;
        }

        public UUID getUser_id() {
            return user_id;
        }

        public void setUser_id(UUID user_id) {
            this.user_id = user_id;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDriver_license_number() {
            return driver_license_number;
        }

        public void setDriver_license_number(String driver_license_number) {
            this.driver_license_number = driver_license_number;
        }

        public String getDriver_license_expiry() {
            return driver_license_expiry;
        }

        public void setDriver_license_expiry(String driver_license_expiry) {
            this.driver_license_expiry = driver_license_expiry;
        }

        public String getLicense_image_url() {
            return license_image_url;
        }

        public void setLicense_image_url(String license_image_url) {
            this.license_image_url = license_image_url;
        }
    }
