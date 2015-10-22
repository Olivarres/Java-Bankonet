package command;

import command.IHMCommand;

public class ExitCommand implements IHMCommand {

	private int id = 0;
	private String lib = ". Fermer l'application";
	
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
