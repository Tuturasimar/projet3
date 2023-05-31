package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.activities.Formation;
import fr.isika.cda.entities.common.FormationStatusEnum;
import fr.isika.cda.entities.common.LocationFormationEnum;
import fr.isika.cda.viewmodels.FormationViewModel;


@Stateless
public class FormationRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Long register(FormationViewModel formationVm) {
		Formation formation = new Formation();
		formation.setLabelActivity(formationVm.getLabelFormation());
		formation.setNbOfDays(formationVm.getNbOfDays());
		formation.setFormationStatus(FormationStatusEnum.GIVEN);
		formation.setLocationFormation(LocationFormationEnum.EXTERN);
		formation.setDescription(null);
		em.persist(formation);
		
		return formation.getId();
	}
	
	public List<Formation> findAllFormations() {
		return em.createQuery("SELECT m FROM Formation f", Formation.class).getResultList();
	}
	
	public void updateFormation(FormationViewModel formationVm) {
		// TODO : fill the rest of the attributes
		Formation formation = new Formation();
		formation.setLabelActivity(formationVm.getLabelFormation());

		em.merge(formation);
	}
}
