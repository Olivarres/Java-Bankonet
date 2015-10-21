package com.bankonet.lib;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.mongodb.ReflectionDBObject;

@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Compte extends ReflectionDBObject implements Serializable {

	/**
	 * 
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String numero;
	private String intitule;
	private Double solde;
	@ManyToOne
	@JoinColumn(name="client")
	private Client client;
	
	public Compte() {	
	}
	
	public Compte(String numero, String intitule, double solde, Client client) {
		super();
		this.numero = numero;
		this.intitule = intitule;
		this.solde = solde;
		this.client = client;
	}

	public abstract boolean checkDebit(double montant);

	public void effectuerVirement(Compte compte, double montant) throws CompteException {
		try {
			this.debiter(montant);
		} catch (CompteException e) {
			throw new CompteException("Virement de " + montant + " de " +  compte.getNumero() + " impossible");
		}
		//try {
			compte.crediter(montant);
//		} catch (CreditException e) {
//			throw new CreditException("Credit de " + montant + " à " + compte.getNumero() + " impossible");
//		}
	}
	
	public void debiter(double montant) throws CompteException {
		if (checkDebit(montant))
			solde -= montant;
		else {
			throw new CompteException("Impossibruh!");
		}
	}
	
	public void crediter(double montant) throws CompteException {
		solde += montant;
	}


	@Override
	public String toString() {
		return String.format("Compte [numero=%s, intitule=%s, solde=%s]", numero, intitule, solde);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	
}
