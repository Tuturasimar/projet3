package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.ColorConfig;
import fr.isika.cda.viewmodels.ColorConfigViewModel;

@Stateless
public class ColorConfigRepository {
	
	@PersistenceContext
	EntityManager em;

	public void update(ColorConfigViewModel colorConfigVm) {
		ColorConfig colorConfig = getColorConfigByCompanyId(colorConfigVm.getCompanyId());
		colorConfig.setBackgroundColor(colorConfigVm.getBackgroundColor());
		colorConfig.setTextColor(colorConfigVm.getTextColor());
		colorConfig.setButtonColor(colorConfigVm.getButtonColor());
		colorConfig.setTitleColor(colorConfigVm.getTitleColor());

	}
	
	public ColorConfig getColorConfigByCompanyId(Long id) {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = (ColorConfig) em.createQuery("SELECT cc from Config c JOIN c.colorConfig cc WHERE c.company.id = :id ", ColorConfig.class)
										.setParameter("id", id)
										.getSingleResult();
		return colorConfig;
	}
	

}
