package test.com.mini.member;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MemberConfig {

	@Bean
	public MongoDatabase mongoDatabase() {
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase db = client.getDatabase("multi"); //db name
		log.info("Create Bean MongoDatabase... multi");
		return db;
	}

	@Bean
	public MongoCollection<Document> member() {
		MongoCollection<Document> collection = mongoDatabase().getCollection("member");
		log.info("Create Bean member...");
		return collection;
	}
	
}
