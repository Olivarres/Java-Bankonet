package ihm;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.bankonet.lib.*;

import command.*;
import dao.DAOFactory;
import dao.DAOFactoryFile;
import dao.DAOFactoryJpa;
import dao.DAOFactoryMongo;
import metier.ClientService;
import metier.ClientServiceImpl;
import metier.CompteService;
import metier.CompteServiceImpl;


public class Conseiller {
	
	private Map<String, Client> clientsList = new HashMap<String, Client>();
	private String intro;
	private DAOFactory factory = new DAOFactoryJpa();
	private CompteService compteService = new CompteServiceImpl(factory.getCompteDAO());
	private ClientService cs = new ClientServiceImpl(factory.getCompteDAO(), factory.getClientDAO(), compteService);
	private ConsoleReader scan = ConsoleReader.getInstance();
	
	
	Conseiller() {
		intro = "*** Application conseiller bancaire ***\n0. Arrêter le programme\n"
				+ "1. Ouvrir un compte courant\n"
				+ "2. Lister tous les clients\n"
				+ "3. Ajout d'un compte Courant\n"
				+ "4. Ajout d'un compte Epargne\n"
				+ "5. Modifier le decouvert\n";
	}
	
	
	public static void main(String[] args) {
		Conseiller conseiller = new Conseiller();
		Map<Integer, IHMCommand> commands = new HashMap<Integer, IHMCommand>();
		List<IHMCommand> staticCommands = Arrays.asList(new ExitCommand(), 
				new NewCCCommand(conseiller.cs, ConsoleReader.getInstance()), 
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
		
	DBManager db = new DBManager();
	db.connectMySQL();
		
		
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
