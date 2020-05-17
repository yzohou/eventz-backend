package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CleParticipantSondage  implements Serializable {

	@Column(name = "mailId")
	private String adresseMail;
	
	@Column(name = "sondageId")
	private Long idSondage;
	
	

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public Long getIdSondage() {
		return idSondage;
	}

	public void setIdSondage(Long idSondage) {
		this.idSondage = idSondage;
	}

	public CleParticipantSondage(String adresseMail, Long idSondage) {
		super();
		this.adresseMail = adresseMail;
		this.idSondage = idSondage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresseMail == null) ? 0 : adresseMail.hashCode());
		result = prime * result + ((idSondage == null) ? 0 : idSondage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CleParticipantSondage other = (CleParticipantSondage) obj;
		if (adresseMail == null) {
			if (other.adresseMail != null)
				return false;
		} else if (!adresseMail.equals(other.adresseMail))
			return false;
		if (idSondage == null) {
			if (other.idSondage != null)
				return false;
		} else if (!idSondage.equals(other.idSondage))
			return false;
		return true;
	}
	
	
	
}
