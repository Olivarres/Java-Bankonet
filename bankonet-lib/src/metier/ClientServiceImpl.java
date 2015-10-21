package metier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteException;
import com.bankonet.lib.FileManager;

import dao.client.ClientDAO;
import dao.compte.CompteDAO;

public class ClientServiceImpl implements ClientService {

	private Map<String, Client> clientsList = new HashMap<String, Client>();
	private Map<Integer, String> steps = new HashMap<>();
	private Map<Integer, String> stepsAjoutCompte = new HashMap<>();
	private FileManager fm = new FileManager();
	private Client currentClient;
	private String intro;
	private ClientDAO cm;
	//private CompteDAO cpm;
	private CompteService cps;
	
	public ClientServiceImpl(CompteDAO compteDAO, ClientDAO clientDAO, CompteService cs) {
		this.cm = clientDAO;
		//this.cpm = compteDAO;
		this.cps = cs;
	
		this.steps.put(1, "Nom du client:");
		this.steps.put(2, "Prenom du client:");
		this.steps.put(3, "Login du client:");
		this.steps.put(4, "pwd");
		this.stepsAjoutCompte.put(0, "Choisir un client:");
		this.stepsAjoutCompte.put(1, "Choisir un c:");
		intro = "*** Application conseiller bancaire ***\n0. Arrêter le programme\n"
				+ "1. Ouvrir un compte courant\n"
				+ "2. Lister tous les clients\n"
				+ "3. Ajout d'un compte Courant\n"
				+ "4. Ajout d'un compte Epargne\n"
				+ "5. Modifier le decouvert\n";
	}

	
public void createClient(String nom, String prenom, String login, String pwd) {
		
		Client client = null;
		StringBuilder builder = new StringBuilder();
		System.out.println("YALO");
		try {
			client = cm.create(nom, prenom, login, pwd);
			System.out.println(client);
			builder.append(nom + '_' + prenom + "_COURANT_1");
			client.creerCompte(new CompteCourant(String.valueOf(client.getComptesList().size()+1), builder.toString(), 0, 500, client));
			System.out.println("YALA");
			cm.save(client);
		} catch (CompteException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void displayClients() {
		StringBuilder builder = new StringBuilder();
		List<Client> clientsList = cm.findAll();
		//Iterator<Client> it = clientsList.values().iterator();
//		while (it.hasNext()) {
//			client = (Client)it.next();
//			builder.append(client.toString() + "Nombre de comptes: " + client.getComptesList().size() + "\n");
//		}
		for (Client c : clientsList) {
			builder.append(c.toString() + "\n"); // + "Nombre de comptes: " + c.getComptesList().size() + "\n");
		}
		System.out.println(builder.toString());
	}
	
	
	public Client getClient(String id) {
		return cm.findById(id);
	}
	
	public List<Client> getClientByName(String name) {
		return cm.findByName(name);
	}
	
	public List<Client> getClientByFirstName(String name) {
		return cm.findByFirstName(name);
	}
	
	public void updateClient(Client client) {
		cm.mergeClient(client);
	}
	
	public void removeClient(Client client) {
		cm.removeById(client);
	}
	
	public void removeAllClients() {
		cm.removeAll();
	}
	

	
	
	

	public CompteService getCps() {
		return cps;
	}

	public void setCps(CompteService cps) {
		this.cps = cps;
	}
	
	

	
}
