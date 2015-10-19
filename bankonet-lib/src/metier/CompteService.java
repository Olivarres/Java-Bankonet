package metier;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;


public interface CompteService {

	public void displayComptes(Class<? extends Compte> type, Client client);
	public Compte choixCompte(Class<? extends Compte> type, Client client);
	public Compte choixCompteVire(String type, Client client);
	public void displayAll(Client client);
	public void faireDepot(Client client);
}
