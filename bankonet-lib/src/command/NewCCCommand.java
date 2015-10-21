package command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.bankonet.lib.Client;
import com.bankonet.lib.ConsoleReader;

import metier.ClientService;

public class NewCCCommand implements IHMCommand {
	private int id = 1;
	private String lib = ". Ouvrir un compte courant";
	private Map<Integer, String> steps = new HashMap();
	private ClientService cs;
	private ConsoleReader scan;
	
	public NewCCCommand(ClientService cs, ConsoleReader scan) {
		this.steps.put(1, "Nom du client:");
		this.steps.put(2, "Prenom du client:");
		this.steps.put(3, "Login du client:");
		this.steps.put(4, "pwd");
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
		Iterator<String> it = this.steps.values().iterator();
		int step = 0;
		String[] tab = new String[3];
		
		for (step =0;step < 3; step++) {
			tab[step] = this.scan.readLine(it.next());
		}
		cs.createClient(tab[0], tab[1], tab[2], "pwd");
	}

}
