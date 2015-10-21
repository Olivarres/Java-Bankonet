package com.bankonet.lib;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import javax.persistence.*;

import com.mongodb.ReflectionDBObject;

@Entity
@NamedQueries ({
@NamedQuery(name="client.findByName", query="select c from Client c where c.nom=:name"),
@NamedQuery(name="client.findByFirstName", query="select c from Client c where c.prenom=:firstname"),
})
public class Client extends ReflectionDBObject implements Serializable {

	
	private String nom;
	private String prenom;
	@Id
	private String identifiant;
	private String pwd;
	@Transient
	private Map<String, Compte> comptesList = new HashMap();
	
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
		this.comptesList.put(compte.getNumero(), compte);

	}
	

	public String synthese(Class<? extends Compte> type) {
		int size = 1;
		if (this.comptesList != null) {
			size = this.comptesList.size() +1;
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
		this.comptesList.remove(compte.getNumero());
	}
	
	public Compte retournerCompte(String numero) throws CompteException {
		Iterator<Compte> it = this.comptesList.values().iterator();
		Compte compte;
		
		if ((compte = this.comptesList.get(numero)) != null) {
			return compte;
		} else {
			throw new CompteException("Compte non trouvé");
		}
	}
	
	public void supprimerCompte(String numero) throws CompteException {
		
		Compte compte;
		
		try {
		compte = this.retournerCompte(numero);
		} catch (CompteException e) {
			throw new CompteException(e.getMessage());
		}
		comptesList.remove(compte.getNumero());
	}
	
	public double calculerAvoirGlobal() {
		double buffer = 0;
		Iterator<Compte> it = this.comptesList.values().iterator();
		//Iterator<Compte> it = this.comptesList.iterator();
		
		while(it.hasNext()) {
			buffer += it.next().getSolde();
		}
		return buffer;
	}
	
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
	public Map<String, Compte> getComptesList() {
		return comptesList;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setComptesList(Map<String, Compte> comptesList) {
		this.comptesList = comptesList;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
