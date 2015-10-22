package command;

import lib.Client;
import lib.ConsoleReader;
import metier.ClientService;

public class DeleteClientCommand implements IHMCommand {
	
	private int id = 9;
	private String lib = ". Supprimer un client";
	private ClientService cs;
	private ConsoleReader scan;
	
	public DeleteClientCommand(ClientService cs, ConsoleReader scan) {
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
		Client client = cs.getClient(scan.readLine("Entrer un login"));
		if (scan.readLine("Valider en saisissant V ou annuler en saisissant A").equals("V")) {
			cs.removeClient(client);
		}
	}

}
