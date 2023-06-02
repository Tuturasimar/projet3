package fr.isika.cda.entities.users;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import fr.isika.cda.entities.common.ClassContextEnum;

@Entity
public class Notification {

	@Id
	@GeneratedValue
	private Long id;

	private String messageContent;

	@Enumerated(EnumType.STRING)
	private ClassContextEnum classContext;

	@ManyToOne
	private User user;
	// creating a foreign key
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

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

	public Long getId() {
		return id;
	}

}