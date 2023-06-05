package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.ColorConfig;
import fr.isika.cda.entities.config.Config;
import fr.isika.cda.viewmodels.ColorConfigViewModel;

@Stateless
public class ColorConfigRepository {
	
	@PersistenceContext
	EntityManager em;

	public void save(ColorConfigViewModel colorConfigVm) {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig.setBackgroundColor(colorConfigVm.getBackgroundColor());
		colorConfig.setTextColor(colorConfigVm.getTextColor());
		colorConfig.setFontColor(colorConfigVm.getFontColor());
		colorConfig.setButtonColor(colorConfigVm.getButtonColor());
		colorConfig.setTitleColor(colorConfigVm.getTitleColor());
	
		Config config = new Config();
		config.setColorConfig(colorConfig);
		
		em.persist(config);
	}
	
	public Long getColorConfigByCompanyId(Long id) {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = (ColorConfig) em.createQuery("SELECT cc from Config c JOIN c.colorConfig cc WHERE c.company =:id ", ColorConfig.class)
										.setParameter("idColorConfigParam", id)
										.getSingleResult();
		return colorConfig.getId();
	}
	

}
