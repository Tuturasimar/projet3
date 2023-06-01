package fr.isika.cda.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class RegisterArBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ArRepository arRepo;
	
	@Inject
	UserRepository userRepo;
	
	@ManagedProperty(value="#{showArBean}")
	private ShowArBean showArBean;
	
	public ShowArBean getShowArBean() {
		return showArBean;
	}
	
	public void setShowArBean(ShowArBean showArBean) {
		this.showArBean = showArBean;
	}

	public String register() {

		// L'utilisateur ne peut atteindre cette méthode que si un AR n'est pas déjà
		// actif pour le mois en cours
		arRepo.register(userRepo.findUserById(SessionUtils.getUserIdFromSession()));
		
		showArBean.getAr();
		
		return "showAr";
	}

}