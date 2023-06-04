package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.repository.CSSRepository;

@ManagedBean
public class CSSDynamicBean {
	
	@Inject
	private CSSRepository CSSRepo;
	
	public String getBackgroundColor() {
		String backgroundColor = "#" + CSSRepo.getBackgroundColor();
		System.out.println(backgroundColor);
		return backgroundColor;
	}
	
	public String getColorTitle() {
		String colorTitle = "#" + CSSRepo.getColorTitle();
		return colorTitle;
	}

	public CSSRepository getCSSRepo() {
		return CSSRepo;
	}

	public void setCSSRepo(CSSRepository cSSRepo) {
		CSSRepo = cSSRepo;
	}
	
	
	

}
