package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String upload(MultipartFile file, String folder) throws IOException {
        Map options = ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "image"
        );
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), options);
        return result.get("secure_url").toString();
    }

    public void delete(String publicId) throws Exception {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    public String extractPublicId(String url) {
        if (url == null || url.isEmpty()) return null;
        try {
            String[] parts = url.split("/upload/");
            if (parts.length < 2) return null;
            String path = parts[1].split("\\?")[0];
            String withoutVersion = path.substring(path.indexOf("/") + 1);
            return withoutVersion.substring(0, withoutVersion.lastIndexOf("."));
        } catch (Exception e) {
            return null;
        }
    }
}