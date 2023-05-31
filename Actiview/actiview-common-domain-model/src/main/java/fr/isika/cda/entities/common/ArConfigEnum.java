package fr.isika.cda.entities.common;

public enum ArConfigEnum {

	
	WEEK ("Semaine"),
	
	MONTH ("Mois");

	private String label;
	
	private ArConfigEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
