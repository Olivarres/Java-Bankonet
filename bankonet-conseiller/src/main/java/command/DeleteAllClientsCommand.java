package command;

import command.IHMCommand;
import lib.ConsoleReader;
import metier.ClientService;
import metier.CompteService;

public class DeleteAllClientsCommand implements IHMCommand {
	
	private int id = 11;
	private String lib = ". Supprimer tous les clients";
	private ClientService cs;
	private ConsoleReader scan;
	
	public DeleteAllClientsCommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
		this.cs = cs;
		this.scan = scan;
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
		if (scan.readLine("Valider en saisissant V ou annuler en saisissant A").equals("V")) {
			cs.removeAllClients();
		}
	}

}
