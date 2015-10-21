package command;

import com.bankonet.lib.Client;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.ConsoleReader;

import metier.ClientService;
import metier.CompteService;

public class AddCCCommand implements IHMCommand {
	
	private int id = 3;
	private String lib = ". Ajout d'un compte courant";
	private ClientService cs;
	private CompteService compteService;
	private ConsoleReader scan;
	
	public AddCCCommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
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
		compteService.ajoutCompte(CompteCourant.class, client);
	}

}
