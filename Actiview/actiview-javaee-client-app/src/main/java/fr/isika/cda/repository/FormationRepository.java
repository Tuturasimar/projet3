package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.common.StatusEnum;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.utils.SessionUtils;
import fr.isika.cda.viewmodels.EditFormationViewModel;
import fr.isika.cda.viewmodels.FormationViewModel;


@Stateless
public class FormationRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Long register(FormationViewModel formationVm) {
		Formation formation = new Formation();
		formation.setLabelActivity(formationVm.getLabelFormation());
		formation.setNbOfDays(formationVm.getNbOfDays());
		formation.setFormationStatus(formationVm.getFormationState());
		formation.setLocationFormation(formationVm.getLocationFormation());
		formation.setDescription(formationVm.getDescription());
		formation.setLocation(formationVm.getLocation());
		formation.setCreator(em.getReference(User.class, SessionUtils.getUserIdFromSession()));
		formation.setStatus(StatusEnum.ACTIVE);
		em.persist(formation);
		
		return formation.getId();
	}
	
	public List<Formation> findAllFormations() {
		return em.createQuery("SELECT f FROM Formation f", Formation.class).getResultList();
	}
	
	public void updateFormation(EditFormationViewModel editFormaVm) {
		// TODO : fill the rest of the attributes
		Formation formation = findById(editFormaVm.getFormationId());
		formation.setLabelActivity(editFormaVm.getLabelFormation());
		formation.setDescription(editFormaVm.getDescription());
		formation.setFormationStatus(editFormaVm.getFormationState());
		formation.setLocation(editFormaVm.getLocation());
		formation.setLocationFormation(editFormaVm.getLocationFormation());
		formation.setNbOfDays(editFormaVm.getNbOfDays());
		formation.setStatus(editFormaVm.getStatus());

		em.merge(formation);
	}
	
	public Formation findById(Long id) {
		Query query = em.createQuery("SELECT f FROM Formation f WHERE f.id = :id", Formation.class);
		query.setParameter("id", id);
		
		return (Formation) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Formation> getAllActiveFormationsFromCompany(Long companyId) {
		Query query = em.createQuery("SELECT f FROM Formation f JOIN f.creator u WHERE f.status = :active AND u.company.id = :companyId", Formation.class);
		query.setParameter("active", StatusEnum.ACTIVE);
		query.setParameter("companyId", companyId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Formation> findAllFormationsByCompanyId(Long companyId) {
		Query query = em.createQuery("SELECT f FROM Formation f JOIN f.creator u WHERE u.company.id = :id", Formation.class);
		query.setParameter("id", companyId);
		
		return query.getResultList();
	}

}
