package fr.isika.cda.entities.ar;

public enum PartDayEnum {

	MORNING ("Matinée"), AFTERNOON ("Après-midi"), ALLDAY("Journée");
	
	private final String label;
	
	private PartDayEnum(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
