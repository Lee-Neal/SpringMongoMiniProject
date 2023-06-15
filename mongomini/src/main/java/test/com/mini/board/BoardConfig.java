package test.com.mini.board;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BoardConfig {

	@Bean
	public MongoDatabase mongoDatabase() {
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase db = client.getDatabase("multi"); //db name
		log.info("Create Bean MongoDatabase... multi");
		return db;
	}

	@Bean
	public MongoCollection<Document> board() {
		MongoCollection<Document> collection = mongoDatabase().getCollection("board");
		log.info("Create Bean board...");
		return collection;
	}
	
}
