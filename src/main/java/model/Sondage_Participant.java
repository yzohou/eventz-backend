package model;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
public class Sondage_Participant implements Serializable {
	
	
	private long id;
	
	private Participant participant;
	
	private Sondage sondage;
	
	
	private DatePossible dateChoisie;
	
	@ManyToOne
	public DatePossible getDateChoisie() {
		return dateChoisie;
	}

	public void setDateChoisie(DatePossible dateChoisie) {
		this.dateChoisie = dateChoisie;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	public Participant getParticipant() {
		return participant;
	}


	public void setParticipant(Participant participant) {
		this.participant = participant;
	}


	@ManyToOne
	public Sondage getSondage() {
		return sondage;
	}


	public void setSondage(Sondage sondage) {
		this.sondage = sondage;
	}

}
