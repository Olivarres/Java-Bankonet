package dao.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.CompteException;


public class ClientDAOFile implements ClientDAO{

	ObjectInputStream ois; 
	ObjectOutputStream oos;
	Map<String, Client> clientsList = findAll();
	private static ClientDAOFile INSTANCE = null;
	
	private ClientDAOFile(){}
	
	public static ClientDAOFile getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new ClientDAOFile();	
		}
		return INSTANCE;
	}
	
	
	@Override
	public Map<String, Client> findAll() {

		clientsList = new HashMap();
		
		try {
	        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../bankonet-lib/data.list"));
	        Object readMap = ois.readObject();
	        if(readMap != null && readMap instanceof HashMap) {
	            clientsList.putAll((HashMap) readMap);
	        }
	        ois.close();
	    } catch (Exception e) {
	        // Catch exceptions
	    }
		return clientsList;
	}

	public void writeData() {
		
		try {
			oos = new ObjectOutputStream (new FileOutputStream("../bankonet-lib/data.list"));
			//System.out.println(clientsList.get("Stanires").getComptesList().size());
			 oos.writeObject(clientsList);
		        oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Client create(String nom, String prenom, String identifiant, String pwd) throws CompteException {
		
		if ((findById(identifiant)) != null) {
			System.out.println(identifiant);
			throw new CompteException("Le client existe déjà");
		}
		return new Client(nom, prenom, identifiant, pwd);
	}

	@Override
	public Client findById(String id) {
		return clientsList.get(id);
	}

	@Override
	public void save(Client client) {
		clientsList.put(client.getIdentifiant(), client);
		this.writeData();
	}
	
	
	

}
