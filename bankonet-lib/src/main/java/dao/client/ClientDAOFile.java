package dao.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import lib.Client;
import lib.CompteException;


public class ClientDAOFile implements ClientDAO{

	ObjectInputStream ois; 
	ObjectOutputStream oos;
	List<Client> clientsList = findAll();
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
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client create(String nom, String prenom, String identifiant, String pwd) throws CompteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByFirstName(String fname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mergeClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
