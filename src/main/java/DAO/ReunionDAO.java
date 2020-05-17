package DAO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import jpa.EntityManagerHelper;
import model.DatePossible;
import model.Participant;
import model.Reunion;
import model.Sondage;
import model.Sondage_Participant;

public class ReunionDAO {
	
	EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();
    
    
    public Reunion add() {
    	tx.begin();
        Reunion reunion = new Reunion();
        try {
            manager.persist(reunion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        return reunion;
    }
    
    
    public List<Reunion> findAll() {
        return manager.createQuery("Select r From Reunion r", Reunion.class).getResultList();
    }

    public void deleteById(int id) {
    	Reunion Reunion = manager.find(Reunion.class, id);
        tx.begin();
        manager.remove(Reunion);
        tx.commit();
    }


	public List<Reunion> findByMesReunions(int id) {
		List<Reunion> reunions = null;
        UtilisateurDAO udoa = new UtilisateurDAO();
        Participant utilisateur =  null;
        try{
            utilisateur = udoa.findById(id);
            TypedQuery<Reunion> query = manager.createQuery(
                    "SELECT r FROM Reunion r WHERE r.createur = :createur", Reunion.class);
            reunions = query.setParameter("createur", utilisateur).getResultList();
        } catch (NoResultException e){
        	e.printStackTrace();
        }

        return reunions;
	}


	public Reunion findById(int id) {
		return manager.find(Reunion.class, id);
	}


	/**
	 * 
	 * @param reunion
	 * @param intitule
	 * @param resume
	 * @param createurId
	 * @param datesChoisies
	 * @return
	 */
	public Reunion addReunion(Reunion reunion, String intitule, String resume, int createurId, JSONArray datesChoisies, JSONArray participants ) {
		tx.begin();
        try {
        	UtilisateurDAO part = new UtilisateurDAO();
        	
        	
        	Sondage sondage = new Sondage();
            sondage.setCreateur( part.findById(createurId) );
            manager.persist(sondage);
            
            if(datesChoisies.length() > 1 ) {
            	if( participants.length() > 0 ) {
            		for(int i=0; i<participants.length(); i++){
                        Participant next = part.findById( (int)participants.get(i) );
                      	 Sondage_Participant sp = new Sondage_Participant();
                      	 sp.setParticipant(next);
                      	 sp.setSondage(sondage);
                      	 manager.persist(sp);
                       }
            	}else {
            		for (Participant next : part.findAll() ) {
                   	 Sondage_Participant sp = new Sondage_Participant();
                   	 sp.setParticipant(next);
                   	 sp.setSondage(sondage);
                   	 manager.persist(sp);
                    }
            	}
            }
            
            
        	
        	reunion.setIntitule(intitule);
        	reunion.setResume(resume);
        	reunion.setCreateur( part.findById(createurId) );
        	reunion.setSondage(sondage);
        	manager.persist(reunion);
        	
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            for(int i=0; i<datesChoisies.length(); i++){
            	DatePossible d = new DatePossible();
                JSONObject row = (JSONObject) datesChoisies.get(i);
                d.setReunion(reunion);
                d.setHasDejeuner(row.getBoolean("aUnDejeuner"));
                d.setDateValue( new Date(sdf.parse(row.getString("date")).getTime()) );
                manager.persist(d);
                
                if(datesChoisies.length() == 1) {
        		      reunion.setDateChoisie( d.getId().intValue() );
                  }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        tx.commit();
        return reunion;
	}

}
