package com.TaDuy.microservices.history_analytics_service.Service.Imp;

import java.io.File;

public interface CloudinaryServiceImp {
    void init();

    String uploadFile(File file, String folder);
}
