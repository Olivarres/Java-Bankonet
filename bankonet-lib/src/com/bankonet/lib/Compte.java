package com.bankonet.lib;

import java.io.Serializable;

public abstract class Compte implements Serializable {

	private String numero;
	private String intitule;
	private double solde;
	
	public Compte() {
		
	}
	
	public Compte(String numero, String intitule, double solde) {
		super();
		this.numero = numero;
		this.intitule = intitule;
		this.solde = solde;
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

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	
}
