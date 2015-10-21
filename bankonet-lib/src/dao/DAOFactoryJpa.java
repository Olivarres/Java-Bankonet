package dao;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.client.ClientDAO;
import dao.client.ClientDAOJpa;
import dao.compte.CompteDAO;
import dao.compte.CompteDAOJpa;

public class DAOFactoryJpa implements DAOFactory {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankonet-lib");
	
	@Override
	public ClientDAO getClientDAO() {
		
		return new ClientDAOJpa(emf);
	}

	@Override
	public CompteDAO getCompteDAO() {

		return new CompteDAOJpa(emf);
	}

}
