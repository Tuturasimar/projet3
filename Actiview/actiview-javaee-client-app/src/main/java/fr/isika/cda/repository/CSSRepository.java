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

}
