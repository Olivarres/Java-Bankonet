package dao.client;

import java.lang.reflect.Modifier;
import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteException;
import com.bankonet.lib.DBManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;


public class ClientDAOMongo implements ClientDAO {
	DBManager dbm = new DBManager();
	
	
	@Override
	public Map<String, Client> findAll() {
		DBCollection coll = dbm.db.getCollection("Client");
		DBCursor cursor = coll.find();
		
		try {
		       while(cursor.hasNext()) {
		    	   System.out.println("YOLO");
		    	   DBObject dbobj = cursor.next();
		          System.out.println(dbobj);
		         
		        
		       }
		    } finally {
		       cursor.close();
		    }
		
		return null;
	}

	@Override
	public Client create(String nom, String prenom, String identifiant, String pwd) throws CompteException {
		
		return new Client(nom, prenom, identifiant, pwd);
	}

	@Override
	public Client findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		
//		client.cp = client.getComptesList().get("1");
//		client.setComptesList(null);
		//Gson gson = new Gson();
		
//                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
//                .serializeNulls()
//					.registerTypeAdapter(Compte.class, new CompteDeserializer());
//			        .registerTypeAdapter(Compte.class, new CompteSerializer()),
             
	
		//DBObject basic = new BasicDBObject("Client", json);
		DBObject basic = new BasicDBObject();
//		basic.put("name", client.getNom());
//		basic.put("prenom", client.getPrenom());
//		basic.put("identifiant", client.getIdentifiant());
//		basic.put("pwd", client.getPwd());
//		basic.put("comptesList", client.getComptesList());
//		DBCollection dbCollection = dbm.db.getCollection("NameColl");          
//		dbCollection.save(basicDBObject);    
		try {
		DBCollection coll = dbm.db.getCollection("Client");
		
		} catch (MongoException e) {
			//System.out.println(e.getMessage());
		}
		
	}

}
