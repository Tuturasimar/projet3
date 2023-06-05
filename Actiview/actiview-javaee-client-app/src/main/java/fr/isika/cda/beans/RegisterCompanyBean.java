package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.component.colorpicker.ColorPicker;

import fr.isika.cda.entities.common.JobEnum;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.config.ColorConfig;
import fr.isika.cda.repository.ColorConfigRepository;
import fr.isika.cda.repository.CompanyRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.ColorConfigViewModel;
import fr.isika.cda.viewmodels.CompanyViewModel;
import fr.isika.cda.viewmodels.UserViewModel;

@ManagedBean
@SessionScoped
public class RegisterCompanyBean {

	@Inject
	CompanyRepository companyRepo;

	@Inject
	UserRepository userRepo;
	
	@Inject
	ColorConfigRepository colorConfigRepo;

	private CompanyViewModel companyViewModel = new CompanyViewModel();

	private UserViewModel userVm = new UserViewModel();
	
	private ColorConfigViewModel colorConfigVm = new ColorConfigViewModel();

	public ColorConfigViewModel getColorConfigVm() {
		return colorConfigVm;
	}

	public void setColorConfigVm(ColorConfigViewModel colorConfigVm) {
		this.colorConfigVm = colorConfigVm;
	}
	
	public CompanyViewModel getCompanyViewModel() {
		return companyViewModel;
	}

	public void setCompanyViewModel(CompanyViewModel companyViewModel) {
		this.companyViewModel = companyViewModel;
	}

	public UserViewModel getUserVm() {
		return userVm;
	}

	public void setUserVm(UserViewModel userVm) {
		this.userVm = userVm;
	}

	public void clear() {
		companyViewModel = new CompanyViewModel();
		userVm = new UserViewModel();
	}

	public String register() {
		if (!companyRepo.checkExistingCompany(companyViewModel)) {

			// Création de la Company
			Long companyId = companyRepo.register(companyViewModel);

			// Lien avec la Company
			userVm.setCompanyId(companyId);
			// On fixe le JobEnum à ADMIN
			userVm.setJobEnum(JobEnum.ADMIN);
			// On instancie une liste pour que la méthode du Repo fonctionne
			List<RoleTypeEnum> role = new ArrayList<RoleTypeEnum>();
			role.add(RoleTypeEnum.ADMINESN);
			userVm.setRoleTypes(role);
			// Création du UserData et User (correspondant à l'admin ESN)
			// Génération d'un login aléatoire

			// TODO l'utilisation du UUID est contraignant pour un utilisateur, trouver un
			// autre moyen. Soit permettre à l'ESN de renseigner lui même login et mdp, soit
			// une autre méthode aléatoire.
			userVm.setLogin(UUID.randomUUID().toString());
			userVm.setPassword(UUID.randomUUID().toString());

			userRepo.registerUser(userVm);
			
//			//Création de la ColorConfig par défaut lié à la company
			colorConfigVm.setBackgroundColor("7FB2AE");
			colorConfigVm.setButtonColor("8FBCB8");
			colorConfigVm.setTextColor("000000");
			colorConfigVm.setTitleColor("e01d1d");
			
			colorConfigRepo.save(colorConfigVm);

			// TODO ajout d'un message de validation "votre inscription a bien été
			// effectuée"
			// TODO ajout d'une notification au superadmin

			return "index";
		} else {
			// TODO ajout d'un message d'erreur
		}
		return "LoginView";

	}

}
