package fr.isika.cda.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.assignement.MissionUser;
import fr.isika.cda.entities.common.ClassContextEnum;
import fr.isika.cda.repository.UserFeedBackRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class ShowMissionUserFeedbackBean {

	@Inject
	private UserFeedBackRepository userFeedbackRepo;

	@ManagedProperty(value = "#{notificationBean}")
	private NotificationBean notifBean;

	public NotificationBean getNotifBean() {
		return notifBean;
	}

	public void setNotifBean(NotificationBean notifBean) {
		this.notifBean = notifBean;
	}

	private List<MissionUser> missionUsers;

	public List<MissionUser> getMissionUsers() {
		return missionUsers;
	}

	public void setMissionUsers(List<MissionUser> missionUsers) {
		this.missionUsers = missionUsers;
	}

	public String showView(Long id) {
		missionUsers = userFeedbackRepo.getAllUserFeedbackByMissionId(id);

		if (missionUsers.size() != 0) {
			return "showMissionUserFeedback";
		} else {

			notifBean.addNotification(SessionUtils.getUserIdFromSession(), "Aucune note pour cette mission",
					ClassContextEnum.DANGER);

			notifBean.load();

			return "missionList";
		}

	}

	public String getMissionLabel() {
		if (missionUsers.size() != 0) {
			return missionUsers.get(0).getMission().getLabelActivity();
		}
		return null;
	}
}
