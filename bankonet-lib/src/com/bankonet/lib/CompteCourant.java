package com.bankonet.lib;

public class CompteCourant extends Compte {

	private double montantDecouvertAutorise;
	static int nbComptesCourants=0;
	
	public CompteCourant() {
		nbComptesCourants++;
	}
	
	public CompteCourant(String num, String intitul, double sold, double montantD, Client client) {
		super(num, intitul, sold, client);
		
		if (sold < 0) {
			System.out.println("Un solde ne peut être négatif\n");
			this.setSolde(0);
		}
		this.setMontantDecouvertAutorise(montantD);
	}
	
	static int getNbComptesCourants() {
		return nbComptesCourants;
	}
	
	public boolean checkDebit(double montant)
	{
		return (this.getSolde() - montant) > -this.getMontantDecouvertAutorise() ? true : false;
	}
	

	public double getMontantDecouvertAutorise() {
		return montantDecouvertAutorise;
	}

	public void setMontantDecouvertAutorise(double montantDecouvertAutorise) {
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}

	@Override
	public String toString() {
		return super.toString() + " Montant Aut.: " + montantDecouvertAutorise;
	}
}
