package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import annotation.BusinessKey;

@Entity
public class Substance  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private Substance(){};
	
	public Substance(String uuid) {
		this.uuid = uuid;
	}

	@BusinessKey
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}
}
