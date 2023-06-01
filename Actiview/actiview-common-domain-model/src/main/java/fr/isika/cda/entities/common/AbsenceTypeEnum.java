package fr.isika.cda.entities.common;

public enum AbsenceTypeEnum {

	DISEASE ("Maladie"), HOLIDAY ("Vacances");
	
	private final String label;
	
	private AbsenceTypeEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
