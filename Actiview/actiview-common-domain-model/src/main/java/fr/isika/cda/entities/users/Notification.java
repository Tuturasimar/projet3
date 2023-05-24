package fr.isika.cda.entities.users;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.entities.common.ClassContextEnum;

@Entity
public class Notification {

	@Id
	@GeneratedValue
	private Long userId;

	private String messageContent;

	@Enumerated(EnumType.STRING)
	private ClassContextEnum classContext;

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public ClassContextEnum getClassContext() {
		return classContext;
	}

	public void setClassContext(ClassContextEnum classContext) {
		this.classContext = classContext;
	}

	public Long getUserId() {
		return userId;
	}

}
