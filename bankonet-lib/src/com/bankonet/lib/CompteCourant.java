package com.bankonet.lib;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries ({
@NamedQuery(name="comptecourant.findByIntitule", query="select c from CompteCourant c where c.intitule=:intitule")
})
public class CompteCourant extends Compte {

	private int montantDecouvertAutorise;
	static int nbComptesCourants=0;
	
	public CompteCourant() {
		
	}
	
	public CompteCourant(String num, String intitul, double sold, int montantD, Client client) {
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
	

	public int getMontantDecouvertAutorise() {
		return montantDecouvertAutorise;
	}

	public void setMontantDecouvertAutorise(int montantDecouvertAutorise) {
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}

	@Override
	public String toString() {
		return super.toString() + " Montant Aut.: " + montantDecouvertAutorise;
	}
}
