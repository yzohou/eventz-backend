package DAO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import jpa.EntityManagerHelper;
import model.DatePossible;
import model.Participant;
import model.Reunion;
import model.Sondage;
import model.Sondage_Participant;



public class SondageDAO {
    EntityManager manager = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = manager.getTransaction();

    public Sondage_Participant updateDateChoisie(int idParticipant, int idSelectedDate, int idReunion) {
    	Sondage_Participant sondagePart = new Sondage_Participant();
    	tx.begin();
    	try {
    		ReunionDAO reuDao = new ReunionDAO();
    		Sondage sondage = reuDao.findById(idReunion).getSondage();
    		Sondage_Participant foundSondage = getSondageParticipant(idParticipant, sondage.getId() );
    		sondagePart.setId(foundSondage.getId());
    		sondagePart.setParticipant(foundSondage.getParticipant());
    		sondagePart.setSondage(foundSondage.getSondage());
	    	DatePossible d = findDatePossibleById(idSelectedDate);
	        sondagePart.setDateChoisie(d);
	        manager.merge(sondagePart);
	        
	        //TODO corriger ces betises dans tout le projet
	        Sondage_Participant sondagePart2 = new Sondage_Participant();
	        sondagePart2.setId(sondagePart.getId() );
	        sondagePart2.setDateChoisie(sondagePart.getDateChoisie() );
	        sondagePart = sondagePart2;
	    } catch (NoResultException nre){
	    }
    	
    	tx.commit();
    	return sondagePart;
    }

    public Reunion findById(int id) {
    	ReunionDAO rdao = new ReunionDAO();
    	Reunion reu = rdao.findById(id);
    	
    	Reunion reunion = new Reunion();
    	reunion.setId(reu.getId());
    	reunion.setIntitule(reu.getIntitule());
    	reunion.setResume(reu.getResume());
    	reunion.setDatesPossibles(reu.getDatesPossibles());
    	
        return reunion;
    }

    public List<Reunion> findByMesSondages(int id) {
        List<Sondage_Participant> Sondage = new ArrayList<Sondage_Participant>();
        List<Reunion> reunion = new ArrayList<Reunion>();
        UtilisateurDAO udoa = new UtilisateurDAO();
        Participant utilisateur =  null;
        try{
            utilisateur = udoa.findById(id);
            TypedQuery<Sondage_Participant> query = manager.createQuery(
                    "SELECT sp FROM Sondage_Participant sp WHERE sp.participant = :participant ", Sondage_Participant.class);
            Sondage = query.setParameter("participant", utilisateur).getResultList();
            
            for(Sondage_Participant sondageparticipant : Sondage) {
            	Reunion renu = new Reunion();
            	renu.setId(sondageparticipant.getSondage().getReunion().getId());
            	renu.setIntitule(sondageparticipant.getSondage().getReunion().getIntitule());
            	renu.setResume(sondageparticipant.getSondage().getReunion().getResume());
            	renu.setPadLink(sondageparticipant.getSondage().getReunion().getPadLink());
            	renu.setClearCode(sondageparticipant.getSondage().getReunion().getClearCode());
            	renu.setDateChoisie(sondageparticipant.getSondage().getReunion().getDateChoisie());
            	renu.setDatesPossibles(sondageparticipant.getSondage().getReunion().getDatesPossibles());
            	reunion.add(renu);
            }
        } catch (NoResultException nre){
        }
        
        return reunion;
    }


    public List<Sondage> findAll() {
        return manager.createQuery("Select s From Sondage s", Sondage.class).getResultList();
    }

    public void deleteById(int id) {
        Sondage sondage = manager.find(Sondage.class, id);
        tx.begin();
        manager.remove(sondage);
        tx.commit();
    }
    
    public Sondage_Participant getSondageParticipant(int idParticipant, int idSondage) {
    	Sondage_Participant sondageParticipant = new Sondage_Participant();
        UtilisateurDAO udoa = new UtilisateurDAO();
        Participant utilisateur =  null;
        Sondage sondage = new Sondage();
        try{
            utilisateur = udoa.findById(idParticipant);
            TypedQuery<Sondage> query = manager.createQuery("SELECT s FROM Sondage s WHERE s.id = :idsondage ", Sondage.class);
            sondage = query.setParameter("idsondage", idSondage).getSingleResult();
            
            TypedQuery<Sondage_Participant> query2 = manager.createQuery(
                    "SELECT sp FROM Sondage_Participant sp WHERE sp.participant = :participant AND sp.sondage = :leSondage", Sondage_Participant.class);
            sondageParticipant = query2.setParameter("participant", utilisateur).setParameter("leSondage", sondage).getSingleResult();
            
            
        } catch (NoResultException nre){
        }
        
        return sondageParticipant;
    	
    }
    
    
    public DatePossible findDatePossibleById(int id) {
    	long idl = id;
        return manager.find(DatePossible.class, idl);
    }
}
