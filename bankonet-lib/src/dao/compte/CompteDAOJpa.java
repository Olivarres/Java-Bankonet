package dao.compte;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;
import com.bankonet.lib.CompteException;
import com.bankonet.lib.DBManager;
import com.mysql.jdbc.Statement;

public class CompteDAOJpa implements CompteDAO {

	@Override
	public Compte[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException {
		int size = 1;
		if (client.getComptesList() != null) {
			size = client.getComptesList().size() +1;
		}
		
		if (type.equals(CompteCourant.class)) {
			return new CompteCourant(String.valueOf(size), lib, 0, 0, client);
		} else {
			return new CompteEpargne(String.valueOf(size), lib, 0, 2, client);
		}
	}

	@Override
	public Compte findById(String id, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte findByType(Class<? extends Compte> type, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Compte compte, Client client) {
		System.out.println(client);
		client.getComptesList().put(compte.getNumero(), compte);
	}

}
