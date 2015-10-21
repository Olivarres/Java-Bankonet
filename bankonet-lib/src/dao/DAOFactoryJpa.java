package dao;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.client.ClientDAO;
import dao.client.ClientDAOJpa;
import dao.compte.CompteDAO;
import dao.compte.CompteDAOJpa;

public class DAOFactoryJpa implements DAOFactory {

	@Override
	public ClientDAO getClientDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankonet-lib");
		return new ClientDAOJpa(emf);
	}

	@Override
	public CompteDAO getCompteDAO() {
		// TODO Auto-generated method stub
		return new CompteDAOJpa();
	}

}
