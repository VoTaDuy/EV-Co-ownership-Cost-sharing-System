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
    public String uploadFile(File file, String folderName) throws IOException {
        String resourceType = "auto";
        Map<String, Object> params = ObjectUtils.asMap(
                "folder", folderName,
                "resource_type", resourceType
        );

        Map<?, ?> uploadResult = cloudinary.uploader().upload(file, params);
        System.out.println("Uploaded file: " + uploadResult);
        return uploadResult.get("secure_url").toString();
    }
}
