package test.com.mini.comment;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class commentConfig {

	@Bean
	public MongoDatabase mongoDatabase() {
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase db = client.getDatabase("multi"); //db name
		log.info("Create Bean MongoDatabase... multi");
		return db;
	}

	@Bean
	public MongoCollection<Document> comment() {
		MongoCollection<Document> collection = mongoDatabase().getCollection("comment");
		log.info("Create Bean comment...");
		return collection;
	}
	
}
