package fr.isika.cda.beans;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.config.Company;
import fr.isika.cda.entities.config.Options;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.repository.ContractRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class DetailsFeatureBean {
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private ContractRepository contractRepository;
	
	private Company company;
	private List<Options> options;
	
	
	@PostConstruct
	private void init() throws Exception {
		
		Long userId = SessionUtils.getUserIdFromSession();
		List<UserRole> roles = userRepository.getAllUserRolesByUserId(userId);
		Optional<UserRole> findFirst = roles.stream()
				.filter(r -> r.getRoleTypeEnum().equals(RoleTypeEnum.ADMINESN))
				.findFirst();
		if(findFirst.isPresent()) {
			company = findFirst.get().getUser().getCompany();
			options = contractRepository.findOptions(company.getContract().getId());
		} else {
			throw new Exception("No ADMINESN role for this user and company");
		}
	}
	
	public Company getCompany() {
		return company;
	}
	
	public List<Options> getOptions() {
		return options;
	}
	
	public boolean hasOptions() {
		return options != null;
	}
}
