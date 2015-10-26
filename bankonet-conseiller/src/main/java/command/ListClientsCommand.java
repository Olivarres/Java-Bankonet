package command;

import command.IHMCommand;
import lib.ConsoleReader;

import metier.ClientService;
import metier.CompteService;

public class ListClientsCommand implements IHMCommand {

	private int id = 2;
	private String lib = ". Lister tous les clients";
	private ClientService cs;
	
	public ListClientsCommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
		this.cs = cs;
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
