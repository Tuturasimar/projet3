package fr.isika.cda.beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Feature;
import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.repository.ConfigRepository;
import fr.isika.cda.repository.ContractRepository;
import fr.isika.cda.repository.FeatureRepository;
import fr.isika.cda.viewmodels.ContractCreationViewModel;

@ManagedBean
@SessionScoped
public class CreateContractBean {

	@Inject
	CompanyRepository companyRepo;

	@Inject
	ConfigRepository configRepo;

	@Inject
	ContractRepository contractRepo;

	@Inject
	FeatureRepository featureRepo;
	
	@ManagedProperty(value="#{registerCompanyBean}")
	private RegisterCompanyBean registerCompanyBean;

	private ContractCreationViewModel contractVM;
	
	private Long companyId ;

	@PostConstruct
	public void init() {
		contractVM = new ContractCreationViewModel();
		
		// Récup un paramètre de requête 
		Map<String, String> requestParameter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String companyIdParam = requestParameter.get("id");
		if(companyIdParam != null) {
			this.companyId = Long.valueOf(companyIdParam);
		}
		// cas ou pas de param => erreur
		this.companyId = registerCompanyBean.getCompanyViewModel().getCompanyId();
	}
	
//	public String showContractCreationForm(Long companyId) {
//		this.setCompanyId(companyId);
//		return "CreateContract";
//	}

	public String register() {
		
		contractVM.setCompany(companyRepo.findById(companyId));
		contractVM.setFeature(featureRepo.findByFeatureId(contractVM.getFeatureId()));
		Long id = contractRepo.register(contractVM);

		System.out.println("Id du contract créé : " + id);
		return "index";
	}

	public List<Feature> findAllFeatures() {
		return featureRepo.findAll();
	}

	//	public List<Feature> getFeatures() {
//		return featureRepo.findAll();
//	}
//
//	public List<String> getLabelOfFeatures() {
//		List<Feature> features = featureRepo.findAll();
//		List<String> labels = new ArrayList<String>();
//		for (Feature feature : features) {
//			labels.add(feature.getLabel());
//		}
//		return labels;
//	}

	// getters & setters

	public ContractCreationViewModel getContractVM() {
		return contractVM;
	}

	public void setContractVM(ContractCreationViewModel contractVM) {
		this.contractVM = contractVM;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public RegisterCompanyBean getRegisterCompanyBean() {
		return registerCompanyBean;
	}

	public void setRegisterCompanyBean(RegisterCompanyBean registerCompanyBean) {
		this.registerCompanyBean = registerCompanyBean;
	}
}
