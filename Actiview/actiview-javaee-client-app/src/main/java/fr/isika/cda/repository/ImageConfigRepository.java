package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.ImageConfig;
import fr.isika.cda.viewmodels.ImageConfigViewModel;

@Stateless
public class ImageConfigRepository {
	
	@PersistenceContext
	private EntityManager em;

	public void update(ImageConfigViewModel imgConfigVm) {
		
		ImageConfig imageConfig = getImageConfigByCompanyId(imgConfigVm.getCompanyId());
		imageConfig.setLogo(imgConfigVm.getLogo());
		imageConfig.setBanner(imgConfigVm.getBanner());

	}

	public List<ImageConfig> findAllConfigs() {
		return em.createQuery("SELECT c from ImageConfig c", ImageConfig.class ).getResultList();
	}
	
	public ImageConfig getImageConfigByCompanyId(Long id) {
		ImageConfig imageConfig = new ImageConfig();
		imageConfig = (ImageConfig) em.createQuery("SELECT ic from Config c JOIN c.imageConfig ic WHERE c.company.id = :id ", ImageConfig.class)
										.setParameter("id", id)
										.getSingleResult();
		return imageConfig;
	}
	

}
