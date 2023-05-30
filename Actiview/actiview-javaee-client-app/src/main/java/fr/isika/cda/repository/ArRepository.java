package fr.isika.cda.repository;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
/**
 * Repository lié au CRA
 * @author Trévor
 *
 */
public class ArRepository {

	@PersistenceContext
	private EntityManager em;
	
	
}
