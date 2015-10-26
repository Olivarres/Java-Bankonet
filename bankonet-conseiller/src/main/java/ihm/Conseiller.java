package ihm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import command.IHMCommand;
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
		List<IHMCommand> staticCommands = new ArrayList<IHMCommand>();
		Reflections reflections = new Reflections("command");
		
		Set<Class<? extends IHMCommand>> subTypes = reflections.getSubTypesOf(IHMCommand.class);
		
		for (Class<? extends IHMCommand> c : subTypes) {
			try {
				staticCommands.add(c.getConstructor(ClientService.class, ConsoleReader.class, CompteService.class)
				.newInstance(conseiller.cs, ConsoleReader.getInstance(), conseiller.compteService));
			} catch (Throwable e) {
				e.getMessage();
			} 
		}
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
