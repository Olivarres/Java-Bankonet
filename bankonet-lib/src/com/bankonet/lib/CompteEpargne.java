package com.bankonet.lib;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries ({
@NamedQuery(name="compteepargne.findByIntitule", query="select c from CompteEpargne c where c.intitule=:intitule")
})
public class CompteEpargne extends Compte {
	
		private double tauxInteret;
		private double plafond;
		
		
	public CompteEpargne() {
		
	}
	
	public CompteEpargne(String ncompte, String nintitule, double nsolde, double tauxI, Client client) {
		super(ncompte, nintitule, nsolde, client);
		tauxInteret = tauxI;
		plafond = 500;
	}
	
	public double getPlafond(){
		return plafond;
	}
	
	
	public void crediter(double montant) throws CompteException {
		System.out.println(this.plafond);
		if ((montant + this.getSolde() > this.plafond)) {
			throw new CompteException("Plafond dépassé");
		}
		else {
			this.setSolde(montant);
		}
	}
	
	public void setPlafond(double plafond) {
		this.plafond = plafond;
	}
	
	public double getTauxInteret() {
		return tauxInteret;
	}
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public boolean checkDebit(double montant)
	{
		return (this.getSolde() - montant) > 0 ? true : false;
	}
//	public String toString() {
//		return "Taux Interet: " + tauxInteret + "% " + numero + ' ' + intitule + ' ' + solde;
//	}
	
	@Override
	public String toString() {
		return super.toString() + " taux Intéret: " + tauxInteret;
	}
}
