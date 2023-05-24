package fr.isika.cda.entities.common;

public enum MissionTypeEnum {

	FPC ("Forfait"), TMC("Régie"), BC("Intercontrat");
	
	private final String label;


	private MissionTypeEnum(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
