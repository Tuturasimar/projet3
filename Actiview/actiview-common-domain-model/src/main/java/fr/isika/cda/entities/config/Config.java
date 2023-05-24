package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Config implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long configId;

	@OneToOne
	@JoinColumn(name="colorConfigId")
	private ColorConfig colorConfig;

	@OneToOne
	@JoinColumn(name="fontConfigId")
	private FontConfig fontConfig;

	@OneToOne
	@JoinColumn(name="imageConfigId")
	private ImageConfig imageConfig;
	
	@OneToOne
	@JoinColumn(name="companyId")
	private Company company;

}
