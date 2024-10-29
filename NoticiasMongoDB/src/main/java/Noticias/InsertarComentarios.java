package Noticias;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class InsertarComentarios {
	public static void main(String[] args) {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase baseDatos = mongoClient.getDatabase("Noticias");
		MongoCollection col_com = baseDatos.getCollection("Comentario");
		MongoCollection col_not = baseDatos.getCollection("Noticia");
		MongoCollection col_usu = baseDatos.getCollection("Usuario");
		

		try {
			MongoCursor<Document> cursor_com = col_com.find().iterator();

			int id = 0;
			while (cursor_com.hasNext()) {
				id = cursor_com.next().getInteger("id");
			}

			id++;
			
			Bson filter = Filters.eq("titulo","Noticia de impacto");

			 Document noticia = (Document) col_not.find()
					.filter(filter).first();
			 
			filter = Filters.eq("nombre_usuario","Frank_blog");
			
			Document usuario = (Document) col_usu.find().filter(filter).first();
			
			int idNot = noticia.getInteger("id");
			
			Document c = new Document("id", id)
					.append("comentario","Increíble la magnitud de esta noticia, no me lo esperaba para nada.")
					.append("fecha", "2024-10-21")
					.append("usuario",
							new Document("id",usuario.getInteger("id"))
					).append("noticia", new Document("id",idNot));
			col_com.insertOne(c);
			
			id++;
			
			 c = new Document("id", id)
					.append("comentario","No puedo creer que esto haya sucedido. ¿Cómo es posible?")
					.append("fecha", "2024-10-21")
					.append("usuario",
							new Document("id",usuario.getInteger("id"))
					).append("noticia", new Document("id",idNot));
			 
			 col_com.insertOne(c);
			 
			 id++;
			 
			 c = new Document("id", id)
					.append("comentario","Esta noticia es realmente impactante, espero que haya más actualizaciones pronto.")
					.append("fecha", "2024-10-21")
					.append("usuario",
							new Document("id",usuario.getInteger("id"))
					).append("noticia", new Document("id",idNot));
			 
			 col_com.insertOne(c);
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

	}
}
