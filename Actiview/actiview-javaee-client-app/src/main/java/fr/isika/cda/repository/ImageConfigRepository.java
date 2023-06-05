package fr.isika.cda.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.Config;
import fr.isika.cda.entities.config.ImageConfig;
import fr.isika.cda.entities.config.TemplateConfig;
import fr.isika.cda.viewmodels.ImageConfigViewModel;

@Stateless
public class ImageConfigRepository {
	
	private static final String SELECT_LOGO_BY_ID_IMAGE_CONFIG_PARAM = "SELECT ic FROM ImageCongif ic WHERE ic.id = :idImageConfigParam";
	
	@PersistenceContext
	private EntityManager em;
	
//	public Long findLogoByImageConfigId(Long id) {
//			ImageConfig imageConfig = new ImageConfig();
//			imageConfig = em
//						.createQuery(SELECT_LOGO_BY_ID_IMAGE_CONFIG_PARAM, ImageConfig.class)
//						.setParameter("idImageConfigParam", id)
//						.getSingleResult();
//			return imageConfig.getId();
//	}
//	
//	public Long ShowLogo(Long id) {
//		Long imageConfigId = findLogoByImageConfigId(id);
//		return imageConfigId;
//		
//		
//	}

	public void save(ImageConfigViewModel imgConfigVm) {
		ImageConfig imageConfig = new ImageConfig();
		imageConfig.setLogo(imgConfigVm.getLogo());
		imageConfig.setBanner(imgConfigVm.getBanner());
		
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setTemplateChoice(imgConfigVm.getTemplateChoice());

		Config config = new Config();
		config.setImageConfig(imageConfig);
		config.setTemplateConfig(templateConfig);

		em.persist(config);
	}

	public List<ImageConfig> findAllConfigs() {
		return em.createQuery("SELECT c from ImageConfig c", ImageConfig.class ).getResultList();
	}
	

}
