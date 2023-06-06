package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.activities.Absence;


@Stateless
public class AbsenceRepository {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Absence> findAllAbsences() {
		Query query = em.createQuery("SELECT abs FROM Absence abs" , Absence.class);
		
		return query.getResultList();
	}

}
