package dao.client;

import java.util.List;
import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.CompteException;

public interface ClientDAO {
	
	public List<Client> findAll();
	public Client create(String nom, String prenom, String identifiant, String pwd) throws CompteException;
	public Client findById(String id);
	public List<Client> findByName(String name);
	public List<Client> findByFirstName(String fname);
	public void mergeClient(Client client);
	public void removeById(Client client);
	public void removeAll();
	public void save(Client client);
}
