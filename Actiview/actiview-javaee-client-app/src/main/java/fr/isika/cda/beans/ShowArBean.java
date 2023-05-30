package fr.isika.cda.beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.repository.ArRepository;
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
		Ar ar = arRepo.findByUserAndCreatedAt(1L, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
		
		if(ar != null) {
			arVm.setAr(ar);
		} else {
			// Création d'un nouveau AR
		}
	}
	
	public void getAr() {
		Ar ar = arRepo.findByUserAndCreatedAt(1L, arVm.getDate().getMonthValue(), arVm.getDate().getYear());
		
		if(ar != null) {
			arVm.setAr(ar);
		} else {
			arVm.setAr(null);
		}
		
	}
	
	public boolean hasSelectedAr()  {
		return this.arVm.getAr() != null;
	}
	
}
