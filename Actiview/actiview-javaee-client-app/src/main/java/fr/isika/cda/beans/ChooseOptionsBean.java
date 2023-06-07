package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import fr.isika.cda.entities.config.Options;
import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.repository.ContractOptionRepository;
import fr.isika.cda.repository.ContractRepository;
import fr.isika.cda.repository.FeatureRepository;
import fr.isika.cda.repository.OptionsRepository;
import fr.isika.cda.viewmodels.ContractOptionsViewModel;

@ManagedBean
@SessionScoped
public class ChooseOptionsBean {

	@Inject
	ContractOptionRepository contractOptionRepo;

	@Inject
	OptionsRepository optionsRepo;
	
	@Inject
	ContractRepository contractRepo;
	
	@ManagedProperty(value = "#{createContractBean}")
	private CreateContractBean createContractBean;

	private ContractOptionsViewModel contractOptionsVM;

	private Long contractId;
	
	private List<Long> selectedOptionsId;
	
	private int nbOfOptions;

	@PostConstruct
	public void init() {
		contractOptionsVM = new ContractOptionsViewModel();	
		selectedOptionsId = new ArrayList<Long>();
//		this.contractId = createContractBean.getContractVM().getContractId();
	}
	
	public String showView(Long id) {
		this.contractId = id;
		contractOptionsVM.setContractId(contractId);
		return "ChooseOptions";
	}

	public String register() {
		contractOptionsVM.setContract(contractRepo.findById(contractOptionsVM.getContractId()));
		setNbOfOptions(contractOptionsVM.getContract().getFeature().getNbOfOptions());
		contractOptionsVM.getContractId();
		contractOptionRepo.register(contractOptionsVM);

		return "index";
	}
	
	//Ne fonctionne pas pour le moment
//	public void updateSelection(AjaxBehaviorEvent event) {
//	    if (selectedOptionsId.size() > nbOfOptions) {
//	      selectedOptionsId.remove(selectedOptionsId.size() - 1);
//	    }
//	  }

	public List<Options> findAllOptions() {
		return optionsRepo.findAll();
	}

	// Getters & setters
	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public ContractOptionsViewModel getContractOptionsVM() {
		return contractOptionsVM;
	}

	public void setContractOptionsVM(ContractOptionsViewModel contractOptionsVM) {
		this.contractOptionsVM = contractOptionsVM;
	}

	public List<Long> getSelectedOptionsId() {
		return selectedOptionsId;
	}

	public void setSelectedOptionsId(List<Long> selectedOptionsId) {
		this.selectedOptionsId = selectedOptionsId;
	}

	public int getNbOfOptions() {
		return nbOfOptions;
	}

	public void setNbOfOptions(int nbOfOptions) {
		this.nbOfOptions = nbOfOptions;
	}

	public CreateContractBean getCreateContractBean() {
		return createContractBean;
	}

	public void setCreateContractBean(CreateContractBean createContractBean) {
		this.createContractBean = createContractBean;
	}
	
	
}
