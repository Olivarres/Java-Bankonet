package dao.compte;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteException;
import com.bankonet.lib.DBManager;

public class CompteDAOMongo implements CompteDAO {
	DBManager dbm = new DBManager();
	@Override
	public Compte[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException {
		
		return null;
	}

	@Override
	public Compte findById(String id, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Compte Compte, Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Compte findByType(Class<? extends Compte> type, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
