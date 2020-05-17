package model;

import javax.persistence.*;

@Entity
public class Participant {
	
	private int id;
	
	private String email;
	
	private String nom;
	
	private String prenom;
	
	private String password;
	
	
	
	public Participant() {
		super();
    }

    public Participant(String nom,String prenom, String Email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = Email;
        this.password = password;
    }
    

    public Participant(String Email) {
        this.email = Email;
    }
    
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
    public String toString() {
        return "Employee [ nom=" + nom + ", prenom=" + prenom;
    }


}
