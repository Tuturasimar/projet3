package fr.isika.cda.entities.common;

public enum JobEnum {
	
	DEV("Développeur"), 
	CHEF_DE_PROJET("Chef de projet"),
	ADMIN ("Administrateur")
	
	;

	private String label;

	private JobEnum(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
