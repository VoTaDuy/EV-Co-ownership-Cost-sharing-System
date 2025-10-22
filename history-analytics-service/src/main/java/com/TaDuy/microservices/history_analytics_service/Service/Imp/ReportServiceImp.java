package com.TaDuy.microservices.history_analytics_service.Service.Imp;

public interface ReportServiceImp {

    String createdPdfFromText(String text) throws Exception;

    String createdExcelFromText(String text) throws Exception;


}
