package ihm;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import command.AddCCCommand;
import command.AddCECommand;
import command.DeleteAllClientsCommand;
import command.DeleteClientCommand;
import command.ExitCommand;
import command.IHMCommand;
import command.InitCommand;
import command.LFFirstNameCommand;
import command.LFNameCommand;
import command.ListClientsCommand;
import command.ModifDecCommand;
import command.ModifyNameCommand;
import command.NewCCCommand;
import dao.DAOFactory;
import dao.DAOFactoryJpa;
import lib.ConsoleReader;
import metier.ClientService;
import metier.ClientServiceImpl;
import metier.CompteService;
import metier.CompteServiceImpl;


public class Conseiller {
	
	private DAOFactory factory = new DAOFactoryJpa();
	private CompteService compteService = new CompteServiceImpl(factory.getCompteDAO());
	private ClientService cs = new ClientServiceImpl(factory.getCompteDAO(), factory.getClientDAO(), compteService);
	private ConsoleReader scan = ConsoleReader.getInstance();
	
	
	Conseiller() {
	}
	
	
	public static void main(String[] args) {
		Conseiller conseiller = new Conseiller();
		Map<Integer, IHMCommand> commands = new HashMap<Integer, IHMCommand>();
		List<IHMCommand> staticCommands = Arrays.asList(new ExitCommand(), 
				new NewCCCommand(conseiller.cs, ConsoleReader.getInstance(), conseiller.compteService), 
				new ListClientsCommand(conseiller.cs, ConsoleReader.getInstance()),
				new AddCCCommand(conseiller.cs, ConsoleReader.getInstance(), conseiller.compteService),
				new AddCECommand(conseiller.cs, ConsoleReader.getInstance(), conseiller.compteService),
				new ModifDecCommand(conseiller.cs, ConsoleReader.getInstance(), conseiller.compteService),
				new InitCommand(conseiller.cs),
				new LFNameCommand(conseiller.cs, ConsoleReader.getInstance()),
				new LFFirstNameCommand(conseiller.cs, ConsoleReader.getInstance()),
				new ModifyNameCommand(conseiller.cs, ConsoleReader.getInstance()),
				new DeleteClientCommand(conseiller.cs, ConsoleReader.getInstance()),
				new DeleteAllClientsCommand(conseiller.cs, ConsoleReader.getInstance()));
		
		
		for (IHMCommand command : staticCommands) {
			commands.put(command.getId(), command);
		}
		
		while(true) {
			for(Iterator<Integer> p = commands.keySet().iterator(); p.hasNext(); ) {
				Integer in = p.next();
				System.out.println(commands.get(in).getId() + commands.get(in).getLib());
			}
			commands.get(conseiller.scan.readInt("")).execute();
		}
	}

}
