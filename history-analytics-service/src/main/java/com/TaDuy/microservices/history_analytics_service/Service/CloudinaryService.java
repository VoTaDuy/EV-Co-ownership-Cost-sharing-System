package com.TaDuy.microservices.history_analytics_service.Service;

import com.TaDuy.microservices.history_analytics_service.Service.Imp.CloudinaryServiceImp;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class CloudinaryService implements CloudinaryServiceImp {

    @Override
    public void init() {

    }

    @Override
    public String uploadFile(File file, String folder) {
        return "";
    }
}
