package ihm;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.bankonet.lib.*;

import dao.DAOFactory;
import dao.DAOFactoryFile;
import dao.DAOFactoryMongo;
import metier.ClientService;
import metier.ClientServiceImpl;
import metier.CompteService;
import metier.CompteServiceImpl;


public class Conseiller {
	
	private Map<String, Client> clientsList = new HashMap<String, Client>();
	private String intro;
	private DAOFactory factory = new DAOFactoryMongo();
	private CompteService compteService = new CompteServiceImpl(factory.getCompteDAO());
	private ClientService cs = new ClientServiceImpl(factory.getCompteDAO(), factory.getClientDAO(), compteService);
	
	
	Conseiller() {
		intro = "*** Application conseiller bancaire ***\n0. Arrêter le programme\n"
				+ "1. Ouvrir un compte courant\n"
				+ "2. Lister tous les clients\n"
				+ "3. Ajout d'un compte Courant\n"
				+ "4. Ajout d'un compte Epargne\n"
				+ "5. Modifier le decouvert\n";
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
			cs.createClient();
		} 
		else if (option.equals("2")) {
			cs.displayClients(this.intro);
		}
		else if (option.equals("3")) {
			cs.ajoutCompte(CompteCourant.class);
		}
		else if (option.equals("4")) {
			cs.ajoutCompte(CompteEpargne.class);
		}
		else if (option.equals("5")) {
			cs.modifDecouvert();
		}
		else if (option.equals("0")) {
			System.out.println("Closing App...");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		Conseiller conseiller = new Conseiller();
		
		
		
		//conseiller.clientsList = conseiller.factory.getClientDAO().findAll();
		//System.out.println(conseiller.clientsList.size());
		while(true)
			conseiller.handleOption(conseiller.getOption(conseiller.intro));       
	}

}
