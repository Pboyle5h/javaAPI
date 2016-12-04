package uwpAPI;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	private MongoClient mongoClient;
	private MongoDatabase db;
	
	public MongoDatabase getDb() {
		return db;
	}

	public MongoConnection(String mongoClient, String db) {
		super();
		this.mongoClient = new MongoClient(new MongoClientURI(mongoClient));
		this.db = this.mongoClient.getDatabase(db);
		
	}

}