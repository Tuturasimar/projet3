package fr.isika.cda.entities.common;

public enum FormationStatusEnum {

	GIVEN ("Donnée"), FOLLOWED ("Suivie");
	
	private final String label;

	private FormationStatusEnum(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
