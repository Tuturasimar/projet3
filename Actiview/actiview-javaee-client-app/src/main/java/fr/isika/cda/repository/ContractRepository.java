package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.entities.config.Options;

@Stateless
public class ContractRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Options> findOptions(long id) {
		try {
		return entityManager
				.createQuery("SELECT o FROM ContractOptions opt JOIN opt.option o WHERE opt.contract.id = :contractIdParam", Options.class)
				.setParameter("contractIdParam", id)
				.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public Feature findFeature(Long idFeature) {
		
		try {
			return entityManager
					.createQuery("SELECT f FROM Contract c JOIN c.feature f WHERE f.id = :id", Feature.class)
					.setParameter("id", idFeature)
					.getSingleResult();
			} catch(NoResultException e) {
				return null;
			}
		
	}
	
}
