package metier;

import java.util.HashMap;
import java.util.Iterator;
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
	private Map<Integer, String> steps = new HashMap();
	private Map<Integer, String> stepsAjoutCompte = new HashMap();
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
	
	public void createClient() {
		
		Iterator<String> it = this.steps.values().iterator();
		int step = 0;
		String[] tab = new String[3];
		StringBuilder builder = new StringBuilder();
		Client client = null;
		
		for (step =0;step < 3; step++) {
			System.out.println(it.next());
			tab[step] = this.Scandat(0);
		}
		try {
			client = cm.create(tab[0], tab[1], tab[2], steps.get(step+1));
			System.out.println(client);
			builder.append(tab[0] + '_' + tab[1] + "_COURANT_1");
			//CompteCourant compte = new CompteCourant(String.valueOf(client.getComptesList().size()+1), builder.toString(), 0, 500, client);
			client.creerCompte(new CompteCourant(String.valueOf(client.getComptesList().size()+1), builder.toString(), 0, 500, client));
			cm.save(client);
		} catch (CompteException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void displayClients(String msg) {
		StringBuilder builder = new StringBuilder();
		Map<String, Client> clientsList = cm.findAll();
		Client client = null;
		
		Iterator<Client> it = clientsList.values().iterator();
		while (it.hasNext()) {
			client = (Client)it.next();
			builder.append(client.toString() + "Nombre de comptes: " + client.getComptesList().size() + "\n");
		}
		System.out.println(builder.toString());
	}
	
	public void choixClient() {
		boolean ok = false;
		
		this.displayClients("");
		while(!ok) {
			System.out.println("Choisir un client:");
			if ((currentClient = cm.findById(this.Scandat(0))) != null) {
				ok = true;
			}
		}
	}
	
	public String Scandat(int flag) {
		String str = "";
		Scanner scanIn = new Scanner(System.in);
		
		 if (flag == 1)
	    	 scanIn.close();
		 
		 
	     str = scanIn.nextLine();
	     return str;
	}

	public void ajoutCompte(Class<? extends Compte> type) {
		String str;
		
		this.choixClient();
		str = this.currentClient.synthese(type);
		try {
			((CompteServiceImpl) this.cps).getCpm().save(((CompteServiceImpl) this.cps).getCpm().create(this.currentClient, str, type), this.currentClient);
		} catch (CompteException e) {
			e.printStackTrace();
		}
		System.out.println("Compte " + this.currentClient.getComptesList().get(String.valueOf(this.currentClient.getComptesList().size())) + " ajouté!\n");
	}
	
	
	public void modifDecouvert() {
		
		boolean ok = false;
		double value;
		Compte compte = null;
		
		this.choixClient();
		compte = cps.choixCompte(CompteCourant.class, this.currentClient);
		while(!ok) {
			System.out.println("Saisir un montant:");
				if ((value = Double.valueOf(Scandat(0))) == 0) {
					ok = true;
				}
				else {
					((CompteCourant)compte).setMontantDecouvertAutorise(value);
					((CompteServiceImpl) this.cps).getCpm().save(compte, this.currentClient);
					ok = true;
				}
		}
		System.out.println(compte);
	}
	
	

	
}
