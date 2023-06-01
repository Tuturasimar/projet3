package fr.isika.cda.beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.ArViewModel;

@ManagedBean
@SessionScoped
public class ShowArBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ArRepository arRepo;

	private ArViewModel arVm = new ArViewModel();

	public ArViewModel getArVm() {
		return arVm;
	}

	public void setArVm(ArViewModel arVm) {
		this.arVm = arVm;
	}

	@PostConstruct
	public void init() {

		// Chercher l'ID de l'utilisateur connecté
		Long id = SessionUtils.getUserIdFromSession();

		Ar ar = arRepo.findByUserAndCreatedAt(id, LocalDate.now().getMonthValue(), LocalDate.now().getYear());

		if (ar != null) {
			arVm.setAr(ar);
		} else {
			// Création d'un nouveau AR
		}
	}

	public void getAr() {
		Long id = SessionUtils.getUserIdFromSession();

		Ar ar = arRepo.findByUserAndCreatedAt(id, arVm.getDate().getMonthValue(), arVm.getDate().getYear());

		if (ar != null) {
			arVm.setAr(ar);
		} else {
			arVm.setAr(null);
		}

	}

	public boolean hasSelectedAr() {
		return this.arVm.getAr() != null;
	}

	public boolean canBeCreated() {

		if (LocalDate.now().getMonthValue() == arVm.getDate().getMonthValue()
				&& LocalDate.now().getYear() == arVm.getDate().getYear()) {
			return true;
		}
		return false;

	}

	public boolean isArADraft() {
		if (arVm.getAr().getStateArEnum() == StateAr.DRAFT) {
			return true;
		}
		return false;
	}

}
