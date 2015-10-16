package com.bankonet.lib;

public class CompteEpargne extends Compte {
	
		private double tauxInteret;
		private double plafond;
		
CompteEpargne() {
		
	}
	public CompteEpargne(String ncompte, String nintitule, double nsolde, double tauxI) {
		super(ncompte, nintitule, nsolde);
		tauxInteret = tauxI;
		plafond = 500;
	}
	
	public double getPlafond(){
		return plafond;
	}
	
	public void crediter(double montant) throws CompteException {
		System.out.println(this.plafond);
		if ((montant + this.getSolde() > this.plafond)) {
			throw new CompteException("Plafond d�pass�");
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
		return super.toString() + " taux Int�ret: " + tauxInteret;
	}
}
