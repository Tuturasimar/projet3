package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.FontConfig;
import fr.isika.cda.viewmodels.FontConfigViewModel;

@Stateless
public class FontConfigRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void update(FontConfigViewModel fontConfigVm) {
		FontConfig fontConfig = getFontConfigByCompanyId(fontConfigVm.getCompanyId());
		fontConfig.setFontFamily(fontConfigVm.getFontFamily());
		
	}
	
	public FontConfig getFontConfigByCompanyId(Long id) {
		FontConfig fontConfig = new FontConfig();
		fontConfig = (FontConfig) em.createQuery("SELECT fc from Config c JOIN c.fontConfig fc WHERE c.company.id = :id ", FontConfig.class)
										.setParameter("id", id)
										.getSingleResult();
		return fontConfig;
	}

}
