package com.TaDuy.microservices.history_analytics_service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HistoryAnalyticsServiceApplication {

	public static void main(String[] args) {
		String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database =mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = database.getCollection("test");
            collection.insertOne(new Document("name", "duy").append("createAt", new Date()));
            for (Document doc : collection.find()){
                System.out.println(doc.toJson());
            }
            System.out.println("Connect Success");
        }catch (Exception e){
            e.printStackTrace();
        }
        SpringApplication.run(HistoryAnalyticsServiceApplication.class, args);
	}

}
