package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
	private long id;

	@OneToOne
	@JoinColumn(name="colorconfig_id")
	private ColorConfig colorConfig;

	@OneToOne
	@JoinColumn(name="fontConfig_id")
	private FontConfig fontConfig;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="imageConfig_id")
	private ImageConfig imageConfig;
	
	@OneToOne
	@JoinColumn(name="company_id")
	private Company company;

	public long getId() {
		return id;
	}

	public ColorConfig getColorConfig() {
		return colorConfig;
	}

	public FontConfig getFontConfig() {
		return fontConfig;
	}

	public ImageConfig getImageConfig() {
		return imageConfig;
	}
	public void setImageConfig(ImageConfig imageConfig) {
		this.imageConfig = imageConfig;
	}
	public Company getCompany() {
		return company;
	}

	
}
