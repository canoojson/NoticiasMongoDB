package Noticias;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class BorrarUltimoComentario {
	public static void main(String[] args) {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase baseDatos = mongoClient.getDatabase("Noticias");
		MongoCollection<Document> col_com = baseDatos.getCollection("Comentario");
		
		try {
			
			int numDoc = (int) col_com.countDocuments();
			
			Bson filter = Filters.eq("id", numDoc);
			
			col_com.deleteOne(filter);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}
}
