package dao;

import dao.client.ClientDAOFile;
import dao.compte.CompteDAO;
import dao.compte.CompteDAOFile;

public class DAOFactoryFile implements DAOFactory {

	@Override
	public ClientDAOFile getClientDAO() {
		// TODO Auto-generated method stub
		return ClientDAOFile.getInstance();
	}

	@Override
	public CompteDAO getCompteDAO() {
		// TODO Auto-generated method stub
		return new CompteDAOFile();
	}
	
//	public CompteDAOFile getCompteDAO() {
//		return new CompteDAOFile;
//	}

}
