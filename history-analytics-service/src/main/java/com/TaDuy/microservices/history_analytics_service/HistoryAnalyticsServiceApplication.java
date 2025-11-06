package com.TaDuy.microservices.history_analytics_service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HistoryAnalyticsServiceApplication {

	public static void main(String[] args) {
        String uri = System.getenv("SPRING_DATA_MONGODB_URI");
        if (uri == null || uri.isEmpty()) {
            uri = "mongodb://localhost:27017"; // fallback khi chạy local
        }

        System.out.println(">>> Using Mongo URI: " + uri);

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = database.getCollection("test");
            collection.insertOne(new Document("name", "duy").append("createAt", new Date()));
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }
            System.out.println(" Mongo connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(HistoryAnalyticsServiceApplication.class, args);

	}
    @PostConstruct
    public void init() {
        System.out.println("SPRING_DATA_MONGODB_URI = " + System.getenv("SPRING_DATA_MONGODB_URI"));
    }

}
