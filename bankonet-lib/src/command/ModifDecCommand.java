package command;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.ConsoleReader;

import metier.ClientService;
import metier.CompteService;

public class ModifDecCommand implements IHMCommand {

	private int id = 5;
	private String lib = ". Modifier le decouvert";
	private ClientService cs;
	private CompteService compteService;
	private ConsoleReader scan;
	
	public ModifDecCommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
		this.cs = cs;
		this.scan = scan;
		this.compteService = compteService;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getLib() {
		return lib;
	}

	@Override
	public void execute() {
		cs.displayClients();
		Client client = cs.getClient(scan.readLine("Choisir un client"));
		compteService.displayComptes(CompteCourant.class, client);
		Compte compte = compteService.getCompte(CompteCourant.class, client, scan.readLine("Choisir le compte à modifier"));
		double decouvert = Double.valueOf(scan.readLine("Choisir le nouveau decouvert"));
		compteService.modifDecouvert(compte, decouvert, client);
	}

}
