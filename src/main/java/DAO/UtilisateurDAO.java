package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import jpa.EntityManagerHelper;
import model.Participant;


public class UtilisateurDAO {
    EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();

    
    /**
     * 
     * @param nom
     * @param prenom
     * @param email
     * @param password
     * @return
     */
    public Participant add(String nom, String prenom, String email, String password) {
        tx.begin();
        Participant p = new Participant();
        try {
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setEmail(email);
            manager.persist(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return p;
    }

    public Participant update(int id, String nom, String prenom, String email, String password) {
        tx.begin();
        Participant u = manager.find(Participant.class, id);
        try {
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setPassword(password);
            manager.merge(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return u;
    }

    public Participant findById(int id) {
        return manager.find(Participant.class, id);
    }


    public List<Participant> findAll() {
        return manager.createQuery("Select u From Participant u", Participant.class).getResultList();
    }

    public void deleteById(int id) {
    	Participant participant = manager.find(Participant.class, id);
        tx.begin();
        manager.remove(participant);
        tx.commit();
    }

    public Participant getLogin(String email, String password) {
    	Participant participant = null;
        try{
            TypedQuery<Participant> query = manager.createQuery(
                    "SELECT u FROM Participant u WHERE u.email = :email AND u.password = :password", Participant.class);
            participant = query.setParameter("email", email)
            		.setParameter("password", password)
            		.getSingleResult();
            
            } catch (NoResultException nre){

        }

        return participant;
    }
}
