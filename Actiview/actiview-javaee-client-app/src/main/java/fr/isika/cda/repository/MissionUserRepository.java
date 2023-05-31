package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.assignement.MissionUser;

/**
 * Repository pour interagir avec les affectations de missions dans la BDD
 * @author Trévor
 *
 */
@Stateless
public class MissionUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<MissionUser> getAllMissionUserByManagerId(Long id){
		Query query =  em.createQuery("SELECT mu FROM MissionUser mu JOIN mu.user u JOIN mu.mission mi WHERE u.fk_manager_id = :id");
		query.setParameter("id", id);
		
		return query.getResultList();
	}
}
