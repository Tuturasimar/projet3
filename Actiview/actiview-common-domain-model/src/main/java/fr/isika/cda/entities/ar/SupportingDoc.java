package fr.isika.cda.entities.ar;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "supporting_doc")
public class SupportingDoc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private ArActivity arActivity;

	public Long getId() {
		return id;
	}
	
	

}
