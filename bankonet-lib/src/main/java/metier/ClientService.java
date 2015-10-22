package metier;

import java.util.List;

import lib.Client;

public interface ClientService {
	public Client createClient(String nom, String prenom, String login, String pwd);
	public void displayClients();
	public Client getClient(String id);
	public List<Client> getClientByName(String name);
	public List<Client> getClientByFirstName(String name);
	public void updateClient(Client client);
	public void removeClient(Client client);
	public void removeAllClients();
}
