package Noticias;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class FiltrosDeBusqueda {
	public static void main(String[] args) {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase baseDatos = mongoClient.getDatabase("Noticias");
		MongoCollection<Document> col_not = baseDatos.getCollection("Noticia");
		MongoCollection<Document> col_usu = baseDatos.getCollection("Usuario");
		MongoCollection<Document> col_com = baseDatos.getCollection("Comentario");
		
		try {
			
			//Listar el título y la fecha de las noticias que tengan el tag A
			
			System.out.println("//////////////////// NOTICIAS CON EL TAG A //////////");
			
			Bson filter = Filters.all("tags", "A");
			
			MongoCursor <Document> cursor_not = col_not.find().filter(filter).iterator();
			
			while(cursor_not.hasNext()) {
				Document n = cursor_not.next();
				System.out.println("Titulo: " + n.getString("titulo"));
				System.out.println("Fecha: " + n.getString("fecha"));
				
				System.out.println("--------------------");
			}
			
			//Listar los usuarios que viven en la ciudad de santander
			
			
			System.out.println("///////////////////// USUARIOS QUE VIVEN EN SANTANDER /////////////////");
			
			filter = Filters.eq("direccion.ciudad", "Santander");
			
			MongoCursor <Document> cursor_usu = col_usu.find().filter(filter).iterator();
			
			while(cursor_usu.hasNext()) {
				Document u = cursor_usu.next();
				System.out.println(u);
				System.out.println("------------------");
			}
			
			System.out.println("/////////////////// USUARIOS QUE TIENEN 1 TELEFONO /////////////");
			
			filter = Filters.size("telefonos", 1);
			
			cursor_usu = col_usu.find().filter(filter).iterator();
			
			while(cursor_usu.hasNext()) {
				Document u = cursor_usu.next();
				System.out.println(u);
				System.out.println("------------------");
			}
			
			System.out.println("//////////////////// LISTAR COMENTARIOS DE UNA FECHA QUE DIGA EL USUARIO ///////////");
			
			System.out.println("Fecha del comentario(aaaa-MM-dd): ");
			Scanner tec = new Scanner(System.in);
			String fecha = tec.next();
			
			filter = Filters.eq("fecha", fecha);
			
		    MongoCursor<Document> cursor_com = col_com.find().filter(filter).iterator();
		    
		    while(cursor_com.hasNext()) {
		    	Document c = cursor_com.next();
		    	System.out.println(c);
		    	System.out.println("-----------------");
		    }
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}
}
