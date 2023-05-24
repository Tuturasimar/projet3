package fr.isika.cda.entities.common;

public enum LocationFormationEnum {

	EXTERN ("Externe"), INTERN ("Interne");
	
	private final String label;

	private LocationFormationEnum(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
