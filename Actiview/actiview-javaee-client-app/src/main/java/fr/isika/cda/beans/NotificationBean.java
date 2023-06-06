package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.common.ClassContextEnum;
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
	/**
	 * Méthode récupérant l'ensemble des notifications de l'utilisateur connecté
	 */
	private void init() {
		notifications = notificationRepo.getAllNotificationsByUserConnected();
	}
	
	public void load() {
		init();
	}
	
	/**
	 * Méthode vérifiant si l'utilisateur possède des notifications
	 * @return true s'il a des notifications | false s'il n'en a pas
	 */
	public boolean isNotification() {
		return notifications.size() != 0 ? true : false;
	}
	
	/**
	 * Méthode pour supprimer toutes les notifications de l'utilisateur connecté
	 */
	public void deleteAllNotifications() {
		for (Notification notification : notifications) {
			notificationRepo.delete(notification);
		}
		load();
	}
	
	/**
	 * Ajoute une notification
	 * @param id L'Id de l'utilisateur qui doit recevoir la notification
	 * @param message Le message renseigné dans la notification
	 * @param bootstrapClass La classe BootStrap utilisée pour le rendu graphique de la notification
	 */
	public void addNotification(Long id, String message, ClassContextEnum bootstrapClass) {
		notificationRepo.createNotification(id, message, bootstrapClass);
	}
	
	
}
