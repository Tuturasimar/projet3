package fr.isika.cda.repository;

import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.ar.Ar;
import fr.isika.cda.entities.ar.StateAr;
import fr.isika.cda.entities.common.ArConfigEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.viewmodels.ArViewModel;

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
			Query query = em.createQuery("SELECT A FROM Ar a " + "WHERE fk_user_id = :id "
					+ "AND MONTH(createdAt) = :month " + "AND YEAR(createdAt) = :year");
			query.setParameter("id", userId);
			query.setParameter("month", month);
			query.setParameter("year", year);

			return (Ar) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		}
	}

	public Ar findArWithUserDataByUserAndCreatedAt(Long userId, int month, int year) {

		try {
			Query query = em.createQuery(
					"SELECT A FROM Ar a JOIN a.user u JOIN u.userData "
					+ "WHERE fk_user_id = :id "
					+ "AND MONTH(a.createdAt) = :month "
					+ "AND YEAR(a.createdAt) = :year");
			query.setParameter("id", userId);
			query.setParameter("month", month);
			query.setParameter("year", year);
			
			return (Ar) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		}
	}
	
	public void register(User user) {
		Ar ar = new Ar();

		ar.setCreatedAt(LocalDate.now());
		ar.setArConfig(ArConfigEnum.MONTH);
		ar.setStateArEnum(StateAr.DRAFT);
		ar.setUpdatedAt(LocalDate.now());
		ar.setUser(user);

		em.persist(ar);

	}

	public Ar findById(Long id) {
		return (Ar) em.createQuery("SELECT a FROM Ar a WHERE a.id = " + id).getSingleResult();
	}

	public void changeState(StateAr state, ArViewModel arVm) {
		Ar ar = findById(arVm.getAr().getId());
		ar.setUpdatedAt(LocalDate.now());
		ar.setStateArEnum(state);
	}

	public void acceptAr(Long arId) {
		Ar ar = findById(arId);
		ar.setStateArEnum(StateAr.VALIDATED);
	}
	
	public void refuseAr(Long arId) {
		Ar ar = findById(arId);
		ar.setStateArEnum(StateAr.DRAFT);
	}
}
