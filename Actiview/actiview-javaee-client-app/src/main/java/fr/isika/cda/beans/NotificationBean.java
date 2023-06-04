package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.Notification;
import fr.isika.cda.repository.NotificationRepository;

@ManagedBean
@SessionScoped
public class NotificationBean {

	@Inject
	NotificationRepository notificationRepo;
	
	private List<Notification> notifications;
	
	public List<Notification> getNotifications() {
		return notifications;
	}
	
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	@PostConstruct
	private void init() {
		notifications = notificationRepo.getAllNotificationsByUserConnected();
	}
	
	public void load() {
		init();
	}
	
	public boolean isNotification() {
		return notifications.size() != 0 ? true : false;
	}
	
	public void deleteAllNotifications() {
		for (Notification notification : notifications) {
			notificationRepo.delete(notification);
		}
		load();
	}
	
	
}
