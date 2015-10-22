package command;

import lib.ConsoleReader;

import metier.ClientService;

public class ListClientsCommand implements IHMCommand {

	private int id = 2;
	private String lib = ". Lister tous les clients";
	private ClientService cs;
	private ConsoleReader scan;
	
	public ListClientsCommand(ClientService cs, ConsoleReader scan) {
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
		cs.displayClients();
	}

}
