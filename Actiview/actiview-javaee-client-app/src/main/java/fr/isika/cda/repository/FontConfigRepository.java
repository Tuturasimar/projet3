package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FontConfigRepository {
	
	@PersistenceContext
	private EntityManager em;

}
