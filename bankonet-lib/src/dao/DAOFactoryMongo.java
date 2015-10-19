package dao;

import dao.client.ClientDAO;
import dao.client.ClientDAOMongo;
import dao.compte.CompteDAO;
import dao.compte.CompteDAOMongo;

public class DAOFactoryMongo implements DAOFactory {

	@Override
	public ClientDAO getClientDAO() {
	
		return new ClientDAOMongo();
	}

	@Override
	public CompteDAO getCompteDAO() {
		
		return new CompteDAOMongo();
	}

}
