package Noticias;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ActualizarPeter {
	public static void main(String[] args) {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase baseDatos = mongoClient.getDatabase("Noticias");
		MongoCollection<Document> col_usu = baseDatos.getCollection("Usuario");
		
		try {
			
			Bson filter = Filters.eq("nombre", "Peter");
			
			col_usu.updateOne(filter, new Document("$set", new Document("cuenta_twitter", "twitPeter")));
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}
}
