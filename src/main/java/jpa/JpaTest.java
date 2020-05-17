package jpa;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DAO.UtilisateurDAO;
import model.DatePossible;
import model.Participant;
import model.Reunion;
import model.Sondage;
import model.Sondage_Participant;

public class JpaTest {
    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    private List<Participant> allUtilisateurs = new ArrayList<>();


    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();
        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.initDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        manager.close();
        System.out.println("fin des operations");


    }

    
    /**
     * 
     * @throws ParseException
     */
    public void initDataBase() throws ParseException {
        this.createUtilisateur();
    }

    
    /**
     * 
     * @throws ParseException
     */
    private void createUtilisateur() throws ParseException {
    	Participant utilisateurs1 = new Participant("ZOHOU", "Yannick", "yzohou@etudiant.univ-rennes1.fr", "test");
    	Participant utilisateurs2 = new Participant("YADOH", "Judicael", "yadoh.judicael@etudiant.univ-rennes1.fr", "test");
    	
        allUtilisateurs.add(utilisateurs1);
        allUtilisateurs.add(utilisateurs2);
        
        manager.persist(utilisateurs1);
        manager.persist(utilisateurs2);

        this.createReunion(utilisateurs1);
    }

    /**
     * 
     * @param participant
     * @throws ParseException
     */
     private void createReunion(Participant participant) throws ParseException {
    	 
    	 Sondage sondage = new Sondage();
         sondage.setCreateur(participant);
         manager.persist(sondage);
         
         for (Participant next : this.allUtilisateurs) {
        	 
        	 Sondage_Participant sp = new Sondage_Participant();
        	 sp.setParticipant(next);
        	 sp.setSondage(sondage);
        	 manager.persist(sp);
         }
         
         Reunion reunion = new Reunion();
         reunion.setIntitule("Reunion SPECIAL covid19");
         reunion.setResume("Cette réunion aura pour objectif d'évaluer notre stratégie de business vu le contexte actuel.");
         reunion.setSondage(sondage);

        manager.persist(reunion);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            DatePossible dates1 = new DatePossible(false,new Date(sdf.parse("2020-05-25 15:00:00").getTime()) ,reunion);
            DatePossible dates2 = new DatePossible(true,new Date(sdf.parse("2019-05-28 10:00:00").getTime()) ,reunion);

            manager.persist(dates1);
            manager.persist(dates2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

     
    public void listUtilisateurs() {
        UtilisateurDAO udao = new UtilisateurDAO();

        List<Participant> resultList = udao.findAll();

        for (Participant next : resultList) {
            System.out.println("Participant suivant: " + next);
        }
    }
 

}
