package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TemplateConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String templateChoice;
	
	public String getTemplateChoice() {
		return templateChoice;
	}
	public void setTemplateChoice(String templateChoice) {
		this.templateChoice = templateChoice;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}

}
