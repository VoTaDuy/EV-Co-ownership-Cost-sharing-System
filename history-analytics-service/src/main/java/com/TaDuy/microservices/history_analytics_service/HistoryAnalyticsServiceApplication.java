package com.TaDuy.microservices.history_analytics_service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

@SpringBootApplication
@ComponentScan(basePackages = "com.TaDuy.microservices.history_analytics_service")
public class HistoryAnalyticsServiceApplication {

    public static void main(String[] args) {
        // Lấy URI từ environment variable trước, fallback là Atlas URI mặc định
        String uri = System.getenv("SPRING_DATA_MONGODB_URI");
        if (uri == null || uri.isEmpty()) {
            // Thay bằng URI Atlas của bạn
            uri = "mongodb+srv://admin:MySecret123@cluster0.bnybmqi.mongodb.net/history_db";
        }

        System.out.println(">>> Using Mongo URI: " + uri);

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("history_db"); // Lưu ý tên DB đúng trên Atlas
            MongoCollection<Document> collection = database.getCollection("test");

            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            System.out.println("Mongo connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(HistoryAnalyticsServiceApplication.class, args);
    }
}
