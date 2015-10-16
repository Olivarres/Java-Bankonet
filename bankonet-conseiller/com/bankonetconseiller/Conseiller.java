package com.bankonetconseiller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.bankonet.lib.*;


public class Conseiller {
	
	private Map<String, CompteCourant> comptesList;
	private Map<String, Client> clientsList = new HashMap<String, Client>();
	private Map<Integer, String> steps = new HashMap();
	private Map<Integer, String> stepsAjoutCompte = new HashMap();
	private FileManager fm = new FileManager();
	private Client currentClient;
	private String intro;
	
	//private Steps steps;
	
	Conseiller() {
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
		if ((client =clientsList.get(tab[2])) == null) {
			client = new Client(tab[0], tab[1], tab[2], steps.get(4));
		}
		

		builder.append(tab[0] + '_' + tab[1] + "_COURANT_1");

		CompteCourant compte = new CompteCourant(String.valueOf(client.getComptesList().size()+1), builder.toString(), 0, 500);
		client.creerCompte(compte);
		if (clientsList.get(tab[2]) == null) {
			clientsList.put(tab[2], client);
		}
		
		fm.writeData(clientsList);
		
		this.handleOption(this.getOption(intro));
	}
	
	public void displayClients(String msg) {
		StringBuilder builder = new StringBuilder();
		Iterator<Client> it = this.clientsList.values().iterator();
		Client client = null;
		
		while (it.hasNext()) {
			client = (Client)it.next();
			builder.append(client.toString() + "Nombre de comptes: " + client.getComptesList().size() + "\n");
		}
		System.out.println(builder.toString());
		if (!msg.equals(""))
			this.handleOption(this.getOption(msg));
	}
	
	public void choixClient() {
		boolean ok = false;
		
		this.displayClients("");
		while(!ok) {
			System.out.println("Choisir un client:");
			if ((currentClient = this.clientsList.get(this.Scandat(0))) != null) {
				ok = true;
			}
		}
	}
	
	public void ajoutCompte(String type) {
		StringBuilder builder = new StringBuilder();
		
		this.choixClient();
		builder.append(
				this.currentClient.getNom() + '_' + 
				this.currentClient.getPrenom() + '_' + type + '_' +
				String.valueOf(this.currentClient.getComptesList().size()+1));
		if (type.equals("COURANT")) {
			this.currentClient.creerCompte(new CompteCourant(String.valueOf(this.currentClient.getComptesList().size()+1), builder.toString(), 0, 0));
		}
		else {
			this.currentClient.creerCompte(new CompteEpargne(String.valueOf(this.currentClient.getComptesList().size()+1), builder.toString(), 0, 2));
		}
		fm.writeData(clientsList);
		System.out.println("Compte " + this.currentClient.getComptesList().get(String.valueOf(this.currentClient.getComptesList().size())) + " ajouté!\n");
		this.handleOption(this.getOption(this.intro));
	}
	
	public void displayComptes(String msg, String type) {
		StringBuilder builder = new StringBuilder();
		Iterator<Compte> it = this.currentClient.getComptesList().values().iterator();
		Compte compte = null;
		
		while (it.hasNext()) {
			compte = it.next();
			if ((compte instanceof CompteEpargne && type.equals("epargne")) || (compte instanceof CompteCourant && type.equals("courant"))) {
				builder.append(compte.toString() + "\n");
			}
			
		}
		System.out.println(builder.toString());
		if (!msg.equals(""))
			this.handleOption(this.getOption(msg));
	}
	
	public Compte choixCompte(String type) {
		boolean ok = false;
		Compte compte = null;
		
		this.displayComptes("", type);
		while(!ok) {
			System.out.println("Choisir un compte " + type);
			if ((compte = this.currentClient.getComptesList().get(this.Scandat(0))) != null) {
				ok = true;
			}
		}
		return compte;
	}
	
	public void modifDecouvert() {
		
		boolean ok = false;
		double value;
		Compte compte = null;
		
		this.choixClient();
		compte = choixCompte("courant");
		while(!ok) {
			System.out.println("Saisir un montant:");
				if ((value = Double.valueOf(Scandat(0))) == 0) {
					ok = true;
				}
				else {
					((CompteCourant)compte).setMontantDecouvertAutorise(value);
					fm.writeData(clientsList);
					ok = true;
				}
		}
		System.out.println(compte);
		this.handleOption(this.getOption(this.intro));
	}
	
	public String Scandat(int flag) {
		String str = "";
		Scanner scanIn = new Scanner(System.in);
		
		 if (flag == 1)
	    	 scanIn.close();
		 
		 
	     str = scanIn.nextLine();
	     return str;
	}
	
	public String getOption(String msg) {
		String cOption;
	     
			System.out.println(msg);
			cOption = this.Scandat(0);
	       return cOption;
	}
	
	public void handleOption(String option) {
		
		if (option.equals("1")) {	
			this.createClient();
		} 
		else if (option.equals("2")) {
			this.displayClients(this.intro);
		}
		else if (option.equals("3")) {
			this.ajoutCompte("COURANT");
		}
		else if (option.equals("4")) {
			this.ajoutCompte("EPARGNE");
		}
		else if (option.equals("5")) {
			this.modifDecouvert();
		}
		else if (option.equals("0")) {
			System.out.println("Closing App...");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		Conseiller conseiller = new Conseiller();
		conseiller.fm.getData(conseiller.clientsList);
		System.out.println(conseiller.clientsList.size());
	    conseiller.handleOption(conseiller.getOption(conseiller.intro));       
	}

}
