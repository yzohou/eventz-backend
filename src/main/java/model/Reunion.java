package model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Reunion {
	

	private int id;
	
	private String intitule;
	
	private String resume;
	
	private Sondage sondage;
	
	private Participant createur;
	
	private List<DatePossible> datesPossibles;
	
	private int dateChoisie;
	
	
	private String clearCode;
	
	
	private String padLink;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="INTITULE")
	public String getIntitule() {
		return intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	@OneToOne
	@JoinColumn(name ="id")
	public Sondage getSondage() {
		return sondage;
	}
	
	public void setSondage(Sondage sondage) {
		this.sondage = sondage;
	}
	
	@Column(name="DATECHOISIE")
	public int getDateChoisie() {
		return this.dateChoisie;
	}
	
	public void setDateChoisie(int dateChoisie) {
		this.dateChoisie = dateChoisie;
	}
	
	
	@Column(name="CLEARCODE")
	public String getClearCode() {
		return this.clearCode;
	}
	
	public void setClearCode(String clearCode) {
		this.clearCode = clearCode;
	}
	
	
	
	@Column(name="PADLINK")
	public String getPadLink() {
		return this.padLink;
	}
	
	public void setPadLink(String padLink) {
		this.padLink = padLink;
	}

	
	
	
	@Column(name="RESUME")
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	
	
	
	@OneToMany(mappedBy="reunion", cascade=CascadeType.PERSIST)
	@JsonManagedReference
	public List<DatePossible> getDatesPossibles() {
		return datesPossibles;
	}

	public void setDatesPossibles(List<DatePossible> datesPossibles) {
		this.datesPossibles = datesPossibles;
	}
	
	
	@ManyToOne
	@JsonBackReference
	public Participant getCreateur() {
		return createur;
	}

	public void setCreateur(Participant createur) {
		this.createur = createur;
	}
	
	
	
	
	
}
