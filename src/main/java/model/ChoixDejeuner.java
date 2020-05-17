package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

//@Entity
public class ChoixDejeuner {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDPARTICIPANT")
	private Long idParticipant;
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="IDREUNION")
	private Long idReunion;
	
	private String preferences;
	
	private String allergies;

	private Participant participant;
	
	private Reunion reunion;
	
	
	public ChoixDejeuner(String preferences, String allergies, Participant participant, Reunion reunion) {
		super();
		this.preferences = preferences;
		this.allergies = allergies;
		this.participant = participant;
		this.reunion = reunion;
	}

	@Column(name="PREFERENCES")
	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	@Column(name="ALLERGIES")
	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)
	@Column(name="PARTICIPANT")
	@JsonBackReference
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)
	@Column(name="REUNION")
	@JsonBackReference
	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
	
	
	
	
	
}
