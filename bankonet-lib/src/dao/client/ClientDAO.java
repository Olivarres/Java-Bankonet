package dao.client;

import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.CompteException;

public interface ClientDAO {
	
	public Map<String, Client> findAll();
	public Client create(String nom, String prenom, String identifiant, String pwd) throws CompteException;
	public Client findById(String id);
	public void save(Client client);
}
