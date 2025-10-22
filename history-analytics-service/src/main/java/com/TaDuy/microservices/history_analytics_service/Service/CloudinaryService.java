package com.TaDuy.microservices.history_analytics_service.Service;

import com.TaDuy.microservices.history_analytics_service.Config.CloudinaryConfig;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.CloudinaryServiceImp;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
public class CloudinaryService implements CloudinaryServiceImp {


    @Autowired
    private CloudinaryConfig cloudinaryConfig;

    private Cloudinary cloudinary;

    @Autowired
    public void init() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudinaryConfig.getCloudName(),
                "api_key", cloudinaryConfig.getApiKey(),
                "api_secret", cloudinaryConfig.getApiSecret()
        ));
    }

    @Override
    public String uploadFile(File file, String folder) {

        try {
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                    "folder", folder
            ));
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Upload file to cloudinary failed: " + e.getMessage());
        }
    }
}
