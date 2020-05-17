package model;

import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Sondage {

	private int id;
	
	private Participant createur;
	
	private List<Sondage_Participant> sondageParticipants;
	
	private Reunion reunion;
	
	
	public Sondage() {
		super();
	}

	public Sondage(int id, Participant createur, List<Sondage_Participant> sondageParticipants, Reunion reunion) {
		super();
		this.id = id;
		this.createur = createur;
		this.sondageParticipants = sondageParticipants;
		this.reunion = reunion;
	}

	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
    @JsonBackReference
	public Participant getCreateur() {
		return createur;
	}

	public void setCreateur(Participant createur) {
		this.createur = createur;
	}

	@OneToMany(mappedBy="sondage", cascade=CascadeType.PERSIST)
	public List<Sondage_Participant> getSondageParticipants() {
		return sondageParticipants;
	}

	public void setSondageParticipants(List<Sondage_Participant> sondageParticipants) {
		this.sondageParticipants = sondageParticipants;
	}
	
	
	@OneToOne(mappedBy = "sondage")
	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	@Override
    public String toString() {
        return "Sondage [id=" + id + "]";
    }
	
}
