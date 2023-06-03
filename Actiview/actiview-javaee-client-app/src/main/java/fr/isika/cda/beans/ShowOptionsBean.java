package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Options;
import fr.isika.cda.repository.OptionsRepository;

@ManagedBean
public class ShowOptionsBean {

	@Inject
	private OptionsRepository optionsRepo;
	
//	private ShowOptionsViewModel soVm = new ShowOptionsViewModel();

	private List<Options> options;

	@PostConstruct
	public void initOptionsList() {
		options = optionsRepo.findAll();
	}

//	public ShowOptionsViewModel getSoVm() {
//		return soVm;
//	}
//
//	public void setSoVm(ShowOptionsViewModel soVm) {
//		this.soVm = soVm;
//	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

}
