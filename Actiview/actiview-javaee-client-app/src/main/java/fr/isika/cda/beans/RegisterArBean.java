package fr.isika.cda.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.repository.ArRepository;
import fr.isika.cda.viewmodels.ArViewModel;

@ManagedBean
@SessionScoped
public class RegisterArBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject ArRepository arRepo;
	
	public void register(ArViewModel arVm) {
		
		// On check si le mois en cours correspond au mois sélectionné
		
		// On check s'il existe déjà un Ar sur ce mois
		
		// Si non
		
		// Création du nouveau Ar
		
		// return sur la vue détailAr
	}
	
	
}
