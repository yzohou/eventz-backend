package model;

import java.util.Date;
import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class DatePossible {

	private Long id;
	
	private boolean hasDejeuner;
	
	private Date dateValue;
	
	private Reunion reunion;
	
	
	public DatePossible(boolean hasDejeuner, Date dateValue, Reunion reunion) {
		super();
		this.hasDejeuner = hasDejeuner;
		this.dateValue = dateValue;
		this.reunion = reunion;
	}

	public DatePossible() {
		super();
	}

	@Column(name="hASDEJEUNER")
	public boolean getHasDejeuner() {
		return hasDejeuner;
	}


	public void setHasDejeuner(boolean hasDejeuner) {
		this.hasDejeuner = hasDejeuner;
	}
	
	@Column(name="DATE")
	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@ManyToOne
	@JsonBackReference
	public Reunion getReunion() {
		return reunion;
	}


	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
	
	
	
	


}
