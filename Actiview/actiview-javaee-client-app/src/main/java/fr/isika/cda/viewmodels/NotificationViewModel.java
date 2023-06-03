package fr.isika.cda.viewmodels;

import fr.isika.cda.entities.common.ClassContextEnum;

public class NotificationViewModel {

	private String message;
	
	private ClassContextEnum bootstrapClass;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ClassContextEnum getBootstrapClass() {
		return bootstrapClass;
	}

	public void setBootstrapClass(ClassContextEnum bootstrapClass) {
		this.bootstrapClass = bootstrapClass;
	}
	
	
}
