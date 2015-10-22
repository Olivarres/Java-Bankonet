package dao.compte;

import java.util.List;

import lib.Client;
import lib.Compte;
import lib.CompteException;

public interface CompteDAO {

	public List<Compte> findAll();
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException;
	public Compte findById(String id, Client client);
	public Compte findByType(Class<? extends Compte> type, Client client);
	public Compte findByIntitule(Class<? extends Compte> type, String intitule, Client client);
	public Compte findByNum(Class<? extends Compte> type, Client client, String num);
	public void save(Class<? extends Compte> type, Compte Compte, Client client);
}
