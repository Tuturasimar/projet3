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
	
	private String template1;
	private String template2;
	private String template3;
	public String getTemplate1() {
		return template1;
	}
	public void setTemplate1(String template1) {
		this.template1 = template1;
	}
	public String getTemplate2() {
		return template2;
	}
	public void setTemplate2(String template2) {
		this.template2 = template2;
	}
	public String getTemplate3() {
		return template3;
	}
	public void setTemplate3(String template3) {
		this.template3 = template3;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	
	

}
