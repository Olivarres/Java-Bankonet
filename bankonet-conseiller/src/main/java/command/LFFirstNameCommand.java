package command;

import java.util.List;

import command.IHMCommand;
import lib.Client;
import lib.ConsoleReader;
import metier.ClientService;
import metier.CompteService;

public class LFFirstNameCommand implements IHMCommand {
	
	private int id = 7;
	private String lib = ". Rechercher un client par son prenom";
	private ClientService cs;
	private ConsoleReader scan;
	
	public LFFirstNameCommand(ClientService cs, ConsoleReader scan, CompteService compteService) {
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
		List<Client> list = cs.getClientByFirstName(scan.readLine("Entrer un prenom"));
		for (Client c :list) {
			System.out.println(c);
		}
	}

}
