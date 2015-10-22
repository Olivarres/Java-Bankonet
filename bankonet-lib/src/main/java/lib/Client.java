package lib;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@NamedQueries ({
@NamedQuery(name="client.findByName", query="select c from Client c where c.nom=:name"),
@NamedQuery(name="client.findByFirstName", query="select c from Client c where c.prenom=:firstname"),
})
public class Client implements Serializable {

	
	private String nom;
	private String prenom;
	@Id
	private String identifiant;
	private String pwd;
	@OneToMany(mappedBy="client", fetch = FetchType.EAGER)
	private List<CompteCourant> ccList = new ArrayList<CompteCourant>();
	@OneToMany(mappedBy="client", fetch = FetchType.EAGER)
	private List<CompteEpargne> ceList = new ArrayList<CompteEpargne>();
	//private Map<String, Compte> comptesList = new HashMap();
	
	public Client() {}
	
	public Client(String nom, String prenom, String identifiant, String pwd) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
//		this.comptesList = new HashMap(); 
		this.pwd = pwd;
	}
	

	public void creerCompte(Compte compte) {
		//this.comptesList.put(compte.getNumero(), compte);

	}
	

	public String synthese(Class<? extends Compte> type) {
		int size = 1;
		if (type.equals(CompteCourant.class)) {
			size = this.ccList.size()+1;
		} else {
			size = this.ceList.size()+1;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(
				this.nom + '_' + 
				this.prenom + '_' + type.getName().substring(type.getName().length()-7).toUpperCase() + '_' +
				String.valueOf(size));
		return builder.toString();
	}
	


	@Override
	public String toString() {
		return String.format("Client [nom=%s, prenom=%s, identifiant=%s]", nom, prenom, identifiant);
	}


	public void supprimerCompte(Compte compte) {
//		this.comptesList.remove(compte.getNumero());
	}
	
	//public Compte retournerCompte(String numero) throws CompteException {
//		Iterator<Compte> it = this.comptesList.values().iterator();
//		Compte compte;
//		
//		if ((compte = this.comptesList.get(numero)) != null) {
//			return compte;
//		} else {
//			throw new CompteException("Compte non trouvé");
//		}
	//}
	
	public void supprimerCompte(String numero) throws CompteException {
		
//		Compte compte;
//		
//		try {
//		compte = this.retournerCompte(numero);
//		} catch (CompteException e) {
//			throw new CompteException(e.getMessage());
//		}
//		comptesList.remove(compte.getNumero());
	}
	
//	public double calculerAvoirGlobal() {
//		double buffer = 0;
//		Iterator<Compte> it = this.comptesList.values().iterator();
//		
//		while(it.hasNext()) {
//			buffer += it.next().getSolde();
//		}
//		return buffer;
//	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<CompteCourant> getCcList() {
		return ccList;
	}

	public void setCcList(List<CompteCourant> ccList) {
		this.ccList = ccList;
	}

	public List<CompteEpargne> getCeList() {
		return ceList;
	}

	public void setCeList(List<CompteEpargne> ceList) {
		this.ceList = ceList;
	}
	
}
