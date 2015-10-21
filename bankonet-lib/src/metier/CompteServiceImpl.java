package metier;

import java.util.Iterator;
import java.util.Scanner;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteException;

import dao.compte.CompteDAO;

public class CompteServiceImpl implements CompteService {

	private CompteDAO cpm;
	
	public CompteServiceImpl(CompteDAO cp) {
		this.cpm = cp;
	}
	
	@Override
	public void displayComptes(Class<? extends Compte> type, Client client) {
		StringBuilder builder = new StringBuilder();
		Iterator<Compte> it = client.getComptesList().values().iterator();
		Compte compte = null;
		
		while (it.hasNext()) {
			compte = it.next();
			if (compte.getClass().equals(type)) {
				builder.append(compte.toString() + "\n");
			}
			
		}
		System.out.println(builder.toString());
		
	}

	public void ajoutCompte(Class<? extends Compte> type, Client client) {
		String str;
		
		str = client.synthese(type);
		try {
			cpm.save(cpm.create(client, str, type), client);
		} catch (CompteException e) {
			e.printStackTrace();
		}
		System.out.println("Compte " + client.getComptesList().get(String.valueOf(client.getComptesList().size())) + " ajouté!\n");
	}
	
	@Override
	public Compte getCompte(Class<? extends Compte> type, Client client, String id) {
		return cpm.findById(id, client);
	}
	
	public Compte getCompteByType(Class<? extends Compte> type, Client client) {
		return cpm.findByType(type, client);
	}
	
	public void displayAll(Client client) {
		StringBuilder builder = new StringBuilder();
		Iterator<Compte> it = client.getComptesList().values().iterator();
		Compte compte = null;
		
		while (it.hasNext()) {
			compte = it.next();
			builder.append(compte.toString() + "\n");
			
		}
		System.out.println(builder.toString());
	}
	
	public Compte getCompteVire(String type, Client client) {
		boolean ok = false;
		Compte compte = null;
		
		this.displayAll(client);
		while(!ok) {
			System.out.println("Choisir un compte à " + type);
			if ((compte = client.getComptesList().get(this.Scandat(0))) != null) {
				ok = true;
			}
		}
		return compte;
	}
	

	public void faireDepot(Client client) {
		boolean ok = false;
		Compte compte = null;
		
		compte = this.getCompteVire("créditer", client);
		while(!ok) {
			System.out.println("Saisir un montant:");
				try {
					compte.crediter(Double.valueOf(Scandat(0)));
					//fm.writeData(clientsList);
					ok = true;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CompteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	
	public void modifDecouvert(Compte compte, double decouvert, Client client) {
			
			((CompteCourant)compte).setMontantDecouvertAutorise(decouvert);
			cpm.save(compte, client);
			System.out.println(compte);
		}
	
	
	public CompteDAO getCpm() {
		return cpm;
	}

	public void setCpm(CompteDAO cpm) {
		this.cpm = cpm;
	}

	public String Scandat(int flag) {
		String str = "";
		Scanner scanIn = new Scanner(System.in);
		
		 if (flag == 1)
	    	 scanIn.close();
		 
		 
	     str = scanIn.nextLine();
	     return str;
	}

}
