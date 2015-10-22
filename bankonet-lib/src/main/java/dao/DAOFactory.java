package dao;

import dao.client.ClientDAO;
import dao.compte.CompteDAO;

public interface DAOFactory {

	abstract ClientDAO getClientDAO();
	abstract CompteDAO getCompteDAO();
}
