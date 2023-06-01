package fr.isika.cda.entities.common;

public enum StatusEnum {
ACTIVE ("Actif"),
INACTIVE ("Inactif");
	
	private String label;
	
	private StatusEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
