package command;

import command.IHMCommand;
import lib.ConsoleReader;
import metier.ClientService;
import metier.CompteService;

public class ExitCommand implements IHMCommand {

	private int id = 0;
	private String lib = ". Fermer l'application";
	
	public ExitCommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
		
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
		System.out.println("Closing App...");
		System.exit(0);

	}

}
