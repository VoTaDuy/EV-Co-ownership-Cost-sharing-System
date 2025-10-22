package com.TaDuy.microservices.history_analytics_service.Service.Imp;

import java.io.File;
import java.io.IOException;

public interface CloudinaryServiceImp {

    String uploadFile(File file, String folder) throws IOException;
}
