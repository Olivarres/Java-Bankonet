package metier;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;


public interface CompteService {

	public void displayComptes(Class<? extends Compte> type, Client client);
	public Compte getCompte(Class<? extends Compte> type, Client client, String id);
	public Compte getCompteVire(String type, Client client);
	public void ajoutCompte(Class<? extends Compte> type, Client client);
	public void displayAll(Client client);
	public void faireDepot(Client client);
	public void modifDecouvert(Compte compte, double decouvert, Client client);
}
