package dao.compte;

import java.util.Iterator;
import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;
import com.bankonet.lib.CompteException;

import dao.DAOFactory;
import dao.DAOFactoryFile;
import dao.client.ClientDAOFile;

public class CompteDAOFile implements CompteDAO {

	
	@Override
	public Compte[] findAll() {
		DAOFactory factory = new DAOFactoryFile();
		Map<String, Client> clients = factory.getClientDAO().findAll();
		Iterator<Compte> it2;
		Iterator<Client> it = clients.values().iterator();
		int sum = 0;
		int i =0;
		Client client;
		Compte compte;
		
		while(it.hasNext()) {
			client = it.next();
			sum += client.getComptesList().size();
		}
		
		Compte[] tabCompte = new Compte[sum];
		it = clients.values().iterator();
		while(it.hasNext()) {
			client = it.next();
			it2 = client.getComptesList().values().iterator();
			while(it2.hasNext()) {
				compte = it2.next();
				tabCompte[i++] = compte;
			}
		}
		
		return tabCompte;
	}

	@Override
	public Compte create(Client client, String lib, String type) throws CompteException {
		if (client.getComptesList().get(String.valueOf(client.getComptesList().size()+1)) != null) {
			throw new CompteException("Compte existant");
		}
		if (type.equals("COURANT")) {
			return new CompteCourant(String.valueOf(client.getComptesList().size()+1), lib, 0, 0, client);
		} else {
			return new CompteEpargne(String.valueOf(client.getComptesList().size()+1), lib, 0, 2, client);
		}
	}

	@Override
	public Compte findById(String id, Client client) {
		return client.getComptesList().get(id);
	}

	@Override
	public void save(Compte compte, Client client) {
		client.getComptesList().put(compte.getNumero(), compte);
		System.out.println(client);
		DAOFactory factory = new DAOFactoryFile();
		((ClientDAOFile) factory.getClientDAO()).writeData();
	}

	
}
