package fr.isika.cda.entities.ar;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.isika.cda.entities.activities.Activity;

@Entity
@Table(name="ar_activity")
public class ArActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	public Activity activity;
	

	public Long getId() {
		return id;
	}
	
	

}
