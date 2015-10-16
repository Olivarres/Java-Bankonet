package dao.compte;

import java.util.Map;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteException;

public interface CompteDAO {

	public Compte[] findAll();
	public Compte create(Client client, String lib, String type) throws CompteException;
	public Compte findById(String id, Client client);
	public void save(Compte Compte, Client client);
}
