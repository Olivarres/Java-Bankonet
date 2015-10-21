package dao.compte;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.Type;

import com.bankonet.lib.Client;
import com.bankonet.lib.Compte;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;
import com.bankonet.lib.CompteException;
import com.bankonet.lib.DBManager;
import com.mysql.jdbc.Statement;

public class CompteDAOJpa implements CompteDAO {

	EntityManagerFactory factory = null;
	EntityManager em;
	
	public CompteDAOJpa(EntityManagerFactory fac) {
		this.factory = fac;
	}
	
	@Override
	public Compte[] findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte create(Client client, String lib, Class<? extends Compte> type) throws CompteException {
		int size = 1;
//		if (client.getComptesList() != null) {
//			size = client.getComptesList().size() +1;
//		}
		
		if (type.equals(CompteCourant.class)) {
			return new CompteCourant(String.valueOf(size), lib, 0, 0, client);
		} else {
			return new CompteEpargne(String.valueOf(size), lib, 0, 2, client);
		}
	}

	@Override
	public Compte findById(String id, Client client) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		Compte compte = new CompteCourant();
		
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		et.commit();
		em.close();
		return null;
	}

	@Override
	public void save(Class<? extends Compte> type, Compte compte, Client client) {
		this.em = factory.createEntityManager();
		//System.out.println(client);
		EntityTransaction et = em.getTransaction();
		et.begin();
		client = em.merge(client);
		et.commit();
		if (type.equals(CompteCourant.class)) {
			client.getCcList().add((CompteCourant)compte);
		}
		else {
			client.getCeList().add((CompteEpargne)compte);
		}
		
		//EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(type.cast(compte));
		et.commit();
		em.close();
	}

}
