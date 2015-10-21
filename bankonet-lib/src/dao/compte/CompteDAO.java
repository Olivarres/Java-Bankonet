package dao.compte;

import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteException;

public interface CompteDAO {

	public Compte[] findAll();
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException;
	public Compte findById(String id, Client client);
	public Compte findByType(Class<? extends Compte> type, Client client);
	public Compte findByIntitule(Class<? extends Compte> type, String intitule, Client client);
	public void save(Class<? extends Compte> type, Compte Compte, Client client);
}
