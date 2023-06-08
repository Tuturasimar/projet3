package fr.isika.cda.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.isika.cda.entities.config.ColorConfig;
import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.Config;
import fr.isika.cda.entities.config.FontConfig;
import fr.isika.cda.entities.config.ImageConfig;

@Stateless
public class ConfigRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long initConfig(Long id) {
		ColorConfig colorConfig = new ColorConfig();
		colorConfig.setBackgroundColor("7FB2AE");
		colorConfig.setButtonColor("8FBCB8");
		colorConfig.setTextColor("000000");
		colorConfig.setTitleColor("FFFFFF");
		
		FontConfig fontConfig = new FontConfig();
		fontConfig.setFontFamily("sans-serif");
		
		ImageConfig imgConfig = new ImageConfig();
		imgConfig.setBanner("BannerDefault.png");
		imgConfig.setLogo("logoDefaut.png");
		
		Company company = em.getReference(Company.class, id);
		
		Config config = new Config();
		config.setImageConfig(imgConfig);
		config.setColorConfig(colorConfig);
		config.setFontConfig(fontConfig);

		config.setCompany(company);
		
		em.persist(config);
		return config.getId();
	}

	
	

}
