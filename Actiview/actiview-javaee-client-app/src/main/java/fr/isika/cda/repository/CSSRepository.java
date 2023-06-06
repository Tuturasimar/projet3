package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.ColorConfig;

@Stateless
public class CSSRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public ColorConfig getColorConfigByCompanyId(Long id) {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig = em.createQuery("SELECT cc FROM Config c JOIN c.colorConfig cc WHERE c.company.id = :id", ColorConfig.class)
						.setParameter("id", id)		
						.getSingleResult();
		return colorConfig;
	}


}
