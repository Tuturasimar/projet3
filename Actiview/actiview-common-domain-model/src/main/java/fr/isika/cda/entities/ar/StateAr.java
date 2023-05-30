package fr.isika.cda.entities.ar;

public enum StateAr {
	
	DRAFT("Brouillon"), INHOLD("En attente"), VALIDATED("Validé");
	
	private final String label;
	
	private StateAr(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
