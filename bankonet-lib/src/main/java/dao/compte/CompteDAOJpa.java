package dao.compte;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import lib.Client;
import lib.Compte;
import lib.CompteCourant;
import lib.CompteEpargne;
import lib.CompteException;

public class CompteDAOJpa implements CompteDAO {

	EntityManagerFactory factory = null;
	EntityManager em;
	
	public CompteDAOJpa(EntityManagerFactory fac) {
		this.factory = fac;
	}
	
	@Override
	public List<Compte> findAll() {
		this.em = factory.createEntityManager();
		List<CompteCourant> listcc;
		List<CompteEpargne> listce;
		List<Compte> listAll = new ArrayList<Compte>();
		TypedQuery<CompteCourant> query = em.createQuery("select c from CompteCourant c", CompteCourant.class);
		listcc = query.getResultList();
		TypedQuery<CompteEpargne> query2 = em.createQuery("select c from CompteEpargne c", CompteEpargne.class);
		listce = query2.getResultList();
		listAll.addAll(listcc);
		listAll.addAll(listce);
		em.close();
		return listAll;
	}

	@Override
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException {
		
		if (type.equals(CompteCourant.class)) {
			return new CompteCourant(String.valueOf(client.getCcList().size()+1), lib, 0, 0, client);
		} else {
			return new CompteEpargne(String.valueOf(client.getCeList().size()+1), lib, 0, 2, client);
		}
	}

	@Override
	public Compte findById(String id, Client client) {
		return null;
	}

	public Compte findByNum(Class<? extends Compte> type, Client client, String num) {
		this.em = factory.createEntityManager();
		Compte compte;
		if (type.equals(CompteCourant.class)) {
			TypedQuery<CompteCourant> query = em.createNamedQuery("comptecourant.findByNum",
					CompteCourant.class)
					.setParameter("num", num);
			compte = query.getResultList().get(0);
			em.close();
		}
		else {
			TypedQuery<CompteEpargne> query = em.createNamedQuery("compteepargne.findByNum",
					CompteEpargne.class)
					.setParameter("num", num);
			compte = query.getResultList().get(0);
			em.close();
		}
		System.out.println("FIN");
		return compte;
	}
	
	public Compte findByIntitule(Class<? extends Compte> type, String intitule, Client client) {
		this.em = factory.createEntityManager();
		if (type.equals(CompteCourant.class)) {
			TypedQuery<CompteCourant> query = em.createNamedQuery("comptecourant.findByIntitule",
					CompteCourant.class)
					.setParameter("intitule", intitule);
			Compte compte = query.getResultList().get(0);
			em.close();
			return compte;
		}
		else {
			TypedQuery<CompteEpargne> query = em.createNamedQuery("compteepargne.findByIntitule",
					CompteEpargne.class)
					.setParameter("intitule", intitule);
			Compte compte = query.getResultList().get(0);
			em.close();
			return compte;
		}
		
	
	}
	
	@Override
	public Compte findByType(Class<? extends Compte> type, Client client) {

		Compte compte = new CompteCourant();
		
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		et.commit();
		em.close();
		return null;
	}

	public void mergeCompte(Compte compte) {
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(compte);
		et.commit();
		em.close();
	}
	
	@Override
	public void save(Class<? extends Compte> type, Compte compte, Client client) {
		this.em = factory.createEntityManager();
		
		
		if (type.equals(CompteCourant.class)) {
			client.getCcList().add((CompteCourant)compte);
		}
		else {
			client.getCeList().add((CompteEpargne)compte);
		}
		//EntityTransaction et1 = em.getTransaction();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(type.cast(compte));
		et.commit();
		em.close();
	}

}
