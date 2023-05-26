package fr.isika.cda.entities.ar;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;

	
	@ManyToOne
	@JoinColumn(name="ar_id")
	private Ar ar;

	public Long getId() {
		return id;
	}


	public Activity getActivity() {
		return activity;
	}


	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	public Ar getAr() {
		return ar;
	}


	public void setAr(Ar ar) {
		this.ar = ar;
	}
	
	

}
