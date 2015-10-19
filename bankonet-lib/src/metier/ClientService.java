package metier;

import com.bankonet.lib.Compte;

public interface ClientService {
	public void createClient();
	public void displayClients(String msg);
	public void choixClient();
	public void ajoutCompte(Class<? extends Compte> type);
	public void modifDecouvert();
}
