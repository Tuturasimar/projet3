package fr.isika.cda.entities.common;

public enum ClassContextEnum {

	DANGER ("danger"),
	INFO ("info"),
	SUCCESS ("success");
	
	private String label;
	
	private ClassContextEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}