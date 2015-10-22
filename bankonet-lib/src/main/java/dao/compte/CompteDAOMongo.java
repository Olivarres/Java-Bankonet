package dao.compte;

import java.util.List;

import lib.Client;
import lib.Compte;
import lib.CompteException;

public class CompteDAOMongo implements CompteDAO {

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException {
		// TODO Auto-generated method stub
		return null;
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
	public Compte findByIntitule(Class<? extends Compte> type, String intitule, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte findByNum(Class<? extends Compte> type, Client client, String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Class<? extends Compte> type, Compte Compte, Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mergeCompte(Compte compte) {
		// TODO Auto-generated method stub
		
	}

	

}
