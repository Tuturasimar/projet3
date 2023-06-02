package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.common.ClassContextEnum;
import fr.isika.cda.entities.users.Notification;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.utils.SessionUtils;

@Stateless
public class NotificationRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void createNotification(Long userId, String message, ClassContextEnum bootStrapClass) {
		Notification notif = new Notification();
		notif.setUser(em.getReference(User.class, userId));
		notif.setClassContext(bootStrapClass);
		notif.setMessageContent(message);
		
		em.persist(notif);
	}
	
	@SuppressWarnings("unchecked")
	public List<Notification> getAllNotificationsByUserConnected(){
		try {
			Query query = em.createQuery("SELECT n FROM Notification n WHERE n.user.id = :userId", Notification.class);
			query.setParameter("userId", SessionUtils.getUserIdFromSession());
			
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
}
