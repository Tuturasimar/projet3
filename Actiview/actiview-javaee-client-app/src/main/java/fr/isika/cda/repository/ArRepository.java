package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.ar.Ar;

@Stateless
/**
 * Repository lié au CRA
 * 
 * @author Trévor
 *
 */
public class ArRepository {

	@PersistenceContext
	private EntityManager em;

	public Ar findByUserAndCreatedAt(Long userId, int month, int year) {

		try {
			Query query = em.createQuery(
					"SELECT A FROM Ar a "
					+ "WHERE fk_user_id = :id "
					+ "AND MONTH(createdAt) = :month "
					+ "AND YEAR(createdAt) = :year");
			query.setParameter("id", userId);
			query.setParameter("month", month);
			query.setParameter("year", year);
			
			return (Ar) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		}
	}

}
