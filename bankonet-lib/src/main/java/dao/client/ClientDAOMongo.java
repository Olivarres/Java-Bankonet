package dao.client;

import java.util.List;
import lib.Client;
import lib.CompteException;
import lib.DBManager;


public class ClientDAOMongo implements ClientDAO {
	DBManager dbm = new DBManager();

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
