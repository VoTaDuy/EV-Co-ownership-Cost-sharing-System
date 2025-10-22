package com.TaDuy.microservices.history_analytics_service.Service;

import com.TaDuy.microservices.history_analytics_service.Service.Imp.ReportServiceImp;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class ReportService implements ReportServiceImp {
    @Override
    public String createdPdfFromText(String text) throws  Exception {

        try {

            File file = new File("reports");
            if (!file.exists()){
                file.mkdirs();
            }
            Document document = new Document();
            String filePath ="reports/report_" + System.currentTimeMillis() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph("Ai Generated Report"));
            document.add(new Paragraph(text));
            document.close();
            return filePath;
        } catch (DocumentException e) {
            throw new RuntimeException("Error Creating PDF" + e.getMessage());
        }
    }

    @Override
    public String createdExcelFromText(String text) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("report");
        String[] lines = text.split("/n");
        for (int i = 0; i < lines.length ; i++ ){
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(lines[i]);
        }
        String filePath = "reports/report_" + System.currentTimeMillis() + ".xlsx";
        try (FileOutputStream out = new FileOutputStream(filePath)){
            workbook.write(out);
        }
        workbook.close();
        return filePath;
    }
}
