package command;

import java.util.List;

import com.bankonet.lib.Client;
import com.bankonet.lib.ConsoleReader;

import metier.ClientService;

public class LFNameCommand implements IHMCommand {
	
	private int id = 6;
	private String lib = ". Rechercher un client par son nom";
	private ClientService cs;
	private ConsoleReader scan;
	
	public LFNameCommand(ClientService cs, ConsoleReader scan) {
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
		List<Client> list = cs.getClientByName(scan.readLine("Entrer un nom"));
		for (Client c :list) {
			System.out.println(c);
		}
	}

}
