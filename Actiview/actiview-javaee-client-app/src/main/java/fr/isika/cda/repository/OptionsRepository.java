package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.Options;

@Stateless
public class OptionsRepository {

	@PersistenceContext
	private EntityManager em;
	
	
	public List<Options> findAll(){
		
		return em.createQuery("SELECT o FROM Options o", Options.class).getResultList();
	}
	
	
	
	
}
