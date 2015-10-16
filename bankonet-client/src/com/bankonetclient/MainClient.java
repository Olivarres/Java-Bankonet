package com.bankonetclient;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.bankonet.lib.*;

public class MainClient {
	
	private Map<String, Client> clientsList = new HashMap<String, Client>();
	private Map<Integer, String> steps = new HashMap();
	private Map<Integer, String> stepsDepot = new HashMap();
	private Map<Integer, String> stepsRetrait = new HashMap();
	private FileManager fm = new FileManager();
	private Client currentClient;
	
	private String intro;
	
	//private Steps steps;
	
	MainClient() {
		this.steps.put(1, "Veuillez entrer votre login:");
		this.steps.put(2, "Password:");
		this.steps.put(3, "Connexion impossible.");
		this.steps.put(4, "pwd");
		this.stepsDepot.put(0, "Choisir le compte à créditer:");
		this.stepsDepot.put(1, "Saisir un montant:");
		this.stepsRetrait.put(0, "Choisir le compte à débiter:");
		this.stepsRetrait.put(1, "Saisir un montant:");
		intro = "*** Application Client ***\n"
				+ "0. Arrêter le programme\n"
				+ "1. Consulter les soldes des comptes\n"
				+ "2. Faire un dépôt.\n"
				+ "3. Faire un retrait.\n"
				+ "4. Faire un virement.\n"
				+ "5. Faire un virement externe.\n";
		currentClient = null;
	}
	
	public void displayComptes(String msg) {
		StringBuilder builder = new StringBuilder();
		Iterator<Compte> it = this.currentClient.getComptesList().values().iterator();
		Compte compte = null;
		
		while (it.hasNext()) {
			compte = it.next();
			builder.append(compte.toString() + "\n");
			
		}
		System.out.println(builder.toString());
		if (!msg.equals(""))
			this.handleOption(this.getOption(msg));
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
	
	public boolean login() {
		int step = 0;
		boolean ok = false;
		String[] tab = new String[2];
		
		for (step =0;!ok;) {
			step = 0;
			System.out.println(this.steps.get(step+1));
			tab[step] = this.Scandat(0);
			if ((currentClient = this.clientsList.get(tab[step])) != null) {
				System.out.println(this.steps.get(++step+1));
				tab[step] = this.Scandat(0);
				if (currentClient.getPwd().equals(tab[step])) {
					ok = true;
				}
			}
		}
		return ok;
	}
	
	public Compte choixCompte(String type) {
		boolean ok = false;
		Compte compte = null;
		
		this.displayComptes("");
		while(!ok) {
			System.out.println("Choisir un compte à " + type);
			if ((compte = this.currentClient.getComptesList().get(this.Scandat(0))) != null) {
				ok = true;
			}
		}
		return compte;
	}
	
	public void faireDepot() {
		int step = 1;
		boolean ok = false;
		Compte compte = null;
		
		compte = this.choixCompte("créditer");
		while(!ok) {
			System.out.println(this.stepsDepot.get(step));
				try {
					compte.crediter(Double.valueOf(Scandat(0)));
					fm.writeData(clientsList);
					ok = true;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CompteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		this.handleOption(this.getOption(intro));
	}
	
	
	
	public void faireRetrait() {
		int step = 1;
		boolean ok = false;
		Compte compte = null;
		double value;
		
		compte = this.choixCompte("débiter");
		while(!ok) {
			System.out.println(this.stepsRetrait.get(step));
				try {
					if ((value = Double.valueOf(Scandat(0))) == 0) {
						ok = true;
					}
					else if (value <= compte.getSolde()) {
						compte.debiter(value);
						fm.writeData(clientsList);
						ok = true;
					}
					else
						System.out.println("Retrait Impossible: Decouvert autorisé dépassé");
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (CompteException e) {
					e.printStackTrace();
				}
			}
		this.handleOption(this.getOption(intro));
	}
	
	public void faireVirement() {
		int step = 1;
		boolean ok = false;
		Compte compteDebit = null;
		Compte compteCredit = null;
		double value;
		
		compteDebit = this.choixCompte("débiter");
		compteCredit = this.choixCompte("créditer");
		
		while(!ok) {
			System.out.println(this.stepsRetrait.get(step));
			
				if ((value = Double.valueOf(Scandat(0))) == 0) {
						ok = true;
				}
				else if (value <= compteDebit.getSolde()) {
					try {
						compteCredit.crediter(value);
						compteDebit.debiter(value);
						fm.writeData(clientsList);
						ok = true;
						} catch (CompteException e) {
							System.out.println(e.getMessage() + ": Retrait impossible.");
							ok = true;
						}
					}
				else {
						System.out.println("Solde insuffisante: Retrait Impossible.");
						this.handleOption(this.getOption(intro));
					}
		}
		this.displayComptes("");
		this.handleOption(this.getOption(intro));
	}
	
	public Compte findCompte() {
		boolean ok = false;
		String nom, prenom, lib = "";
		Iterator<Client> it = this.clientsList.values().iterator();
		Iterator<Compte> it2;
		Client client;
		Compte compte = null;
		
		while (!ok) {
			System.out.println("Choisir un nom:");
			nom = Scandat(0);
			System.out.println("Choisir un prenom:");
			prenom = Scandat(0);
			System.out.println("Choisir le libellé:");
			lib = Scandat(0);
			while (it.hasNext()) {
				client = it.next();
				if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {
					 it2 = client.getComptesList().values().iterator();
					while(it2.hasNext()) {
						compte = it2.next();
						if (compte.getIntitule().equals(lib)) {
							return compte;
						}
					}
				}
			}
		}
		return compte;
	}
	
	public void faireVirementExt() {
		int step = 1;
		boolean ok = false;
		Compte compteDebit = null;
		Compte compteCredit = null;
		double value;
		
		compteDebit = this.choixCompte("débiter");
		compteCredit = this.findCompte();
		
		while(!ok) {
			System.out.println(this.stepsRetrait.get(step));
			
				if ((value = Double.valueOf(Scandat(0))) == 0) {
						ok = true;
				}
				else if (value <= compteDebit.getSolde()) {
					try {
						compteCredit.crediter(value);
						compteDebit.debiter(value);
						fm.writeData(clientsList);
						ok = true;
						} catch (CompteException e) {
							System.out.println(e.getMessage() + ": Retrait impossible.");
							ok = true;
						}
					}
				else {
						System.out.println("Solde insuffisante: Retrait Impossible.");
						this.handleOption(this.getOption(intro));
					}
		}
		this.displayComptes("");
		this.handleOption(this.getOption(intro));
	}
	
	public void handleOption(String option) {
		
		if (option.equals("1")) {
			this.displayComptes(this.intro);
		}
		else if (option.equals("2")) {
			this.faireDepot();
		}
		else if (option.equals("3")) {
			this.faireRetrait();
		}
		else if (option.equals("4")) {
			this.faireVirement();
		}
		else if (option.equals("5")) {
			this.faireVirementExt();
		}
		else if (option.equals("0")) {
			System.out.println("Closing App...");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		MainClient mainClient = new MainClient();
		mainClient.fm.getData(mainClient.clientsList);
		
		System.out.println(mainClient.clientsList.size());
		
		if (mainClient.login()) {
			mainClient.handleOption(mainClient.getOption(mainClient.intro));
		}
	}

}
