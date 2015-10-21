package dao.client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bankonet.lib.Client;
import com.bankonet.lib.CompteCourant;
import com.bankonet.lib.CompteEpargne;
import com.bankonet.lib.CompteException;

public class ClientDAOJpa implements ClientDAO {

	EntityManagerFactory factory = null;
	EntityManager em;
	
	public ClientDAOJpa(EntityManagerFactory fac) {
		this.factory = fac;
	}
	
	@Override
	public List<Client> findAll() {
		this.em = factory.createEntityManager();
		TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);
		List<Client> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public Client create(String nom, String prenom, String identifiant, String pwd) throws CompteException {
		return new Client(nom, prenom, identifiant, pwd);
	}

	@Override
	public Client findById(String id) {
		
		this.em = factory.createEntityManager();
		Client client = em.find(Client.class, id);
		Iterator<CompteCourant> it1 = client.getCcList().iterator();
		Iterator<CompteEpargne> it2 = client.getCeList().iterator();
		it1.next();
		it2.next();
		em.close();
		return client;
	}
	
	public List<Client> findByName(String name) {
		this.em = factory.createEntityManager();
		TypedQuery<Client> query = em.createNamedQuery("client.findByName",
				Client.class)
				.setParameter("name", name);
		
		List<Client> list = query.getResultList();
		em.close();
		
		return list;
	}
	
	public List<Client> findByFirstName(String fname) {
		this.em = factory.createEntityManager();
		TypedQuery<Client> query = em.createNamedQuery("client.findByFirstName",
				Client.class)
				.setParameter("firstname", fname);
		List<Client> list = query.getResultList();
		em.close();
		
		return list;
	}

	public void mergeClient(Client client) {
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(client);
		et.commit();
		em.close();
	}
	
	public void removeById(Client client) {
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(em.merge(client));
		et.commit();
		em.close();
	}
	
	public void removeAll() {
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Query query = em.createNativeQuery("DELETE FROM client");
		System.out.println(query.executeUpdate() + " ont été supprimé\n");
		et.commit();
		em.close();
	}
	
	@Override
	public void save(Client client) {
		this.em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(client);
		et.commit();
		em.close();
	}

}
