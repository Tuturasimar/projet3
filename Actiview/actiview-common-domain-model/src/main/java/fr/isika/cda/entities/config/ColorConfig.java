package fr.isika.cda.entities.config;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ColorConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String buttonColor;
	private String fontColor;
	private String backgroundColor;
	private String titleColor;
	private String textColor;

	public String getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(String buttonColor) {
		this.buttonColor = buttonColor;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public long getId() {
		return id;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

}
