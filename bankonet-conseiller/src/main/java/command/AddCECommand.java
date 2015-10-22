package command;

import command.IHMCommand;
import lib.Client;
import lib.CompteEpargne;
import lib.ConsoleReader;
import metier.ClientService;
import metier.CompteService;

public class AddCECommand implements IHMCommand {
	
	private int id = 4;
	private String lib = ". Ajout d'un compte epargne";
	private ClientService cs;
	private CompteService compteService;
	private ConsoleReader scan;
	
	public AddCECommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
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
		compteService.ajoutCompte(CompteEpargne.class, client);
	}

}
