package fr.isika.cda.entities.common;

public enum RoleTypeEnum {
	ADMINESN("Admin ESN"), 
	MANAGER("Manager"), 
	SALARIE("Salarié"), 
	SUPERADMIN("Super admin")
	;

	private String label;

	private RoleTypeEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}