package fr.isika.cda.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.config.ImageConfig;
import fr.isika.cda.repository.ImageConfigRepository;

@ManagedBean
@SessionScoped
public class PreviewBean {

	@Inject
	private ImageConfigRepository imgConfigRepo;

	private List<ImageConfig> configList;
	
	@PostConstruct
	private void init() {
		configList = imgConfigRepo.findAllConfigs();
	}
	
	public List<ImageConfig> getConfigList() {
		return configList;
	}
	public void setConfigList(List<ImageConfig> configList) {
		this.configList = configList;
	}
	
}
