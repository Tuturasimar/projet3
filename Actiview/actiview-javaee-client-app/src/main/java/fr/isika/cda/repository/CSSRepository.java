package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.ColorConfig;

@Stateless
public class CSSRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public String getBackgroundColor() {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = em.createQuery("SELECT cc FROM ColorConfig cc", ColorConfig.class)
						.getSingleResult();
		return colorConfig.getBackgroundColor();
	}
	
	public String getColorTitle() {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = em.createQuery("SELECT cc FROM ColorConfig cc", ColorConfig.class)
						.getSingleResult();
		return colorConfig.getTitleColor();
	}
	
	public String getColorText() {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = em.createQuery("SELECT cc FROM ColorConfig cc", ColorConfig.class)
						.getSingleResult();
		return colorConfig.getTextColor();
	}
	
	public String getColorButton() {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = em.createQuery("SELECT cc FROM ColorConfig cc", ColorConfig.class)
						.getSingleResult();
		return colorConfig.getButtonColor();
	}
	
	public void delete(ColorConfig colorConfig) {
		em.remove(em.contains(colorConfig) ? colorConfig : em.merge(colorConfig));
	}
	
	public ColorConfig getAllColorConfig() {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = em.createQuery("SELECT cc FROM ColorConfig cc", ColorConfig.class)
						.getSingleResult();
		return colorConfig;
	}
	
	public void checkExistingColorConfig() {
		ColorConfig colorConfigToDelete = getAllColorConfig();
		if (colorConfigToDelete != null) {
			delete(colorConfigToDelete);
		}
	}


}
