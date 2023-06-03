package fr.isika.cda.beans;


import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.users.UserRole;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.viewmodels.UpdateUserRoleViewModel;

@ManagedBean
@SessionScoped
public class UpdateUserRoleBean {

	private UpdateUserRoleViewModel updateUserRoleVM;

	@Inject
	private UserRepository userRepo;
	
	/**
	 * Méthode qui permet de renvoyer sur un formulaire de modification du rôle d'un user
	 * @param id du userVM de la vue UserList qui sert à retrouver le user et le userData en bdd
	 * @return la vue du formulaire de modif du userRole
	 */
	public String showUpdateUserRole(Long id) {
		//liste des userRole qui sont récupérés à partir de l'id du user
		List<UserRole> rolesToUpdate =  userRepo.getAllUserRolesByUserId(id);
			
		
		// Java 8 Stream API
		// on prend les roles, on fait un flux parallele dessus puis on map chaque role à l'enum et on collecte le tout
		List<RoleTypeEnum> roleTypesFromRoles = rolesToUpdate.parallelStream()
				.map(role -> role.getRoleTypeEnum())
				.collect(Collectors.toList());
		
		updateUserRoleVM = new UpdateUserRoleViewModel(id);
		updateUserRoleVM.setRoleTypes(roleTypesFromRoles);
		return "UpdateUserRole.xhtml";
	}
	
	/**
	 * Méthode qui permet d'enregistrer la modification d'un userRole en bdd
	 * @return la vue UserList
	 */
	public String updateUserRole() {
		
		Long id = updateUserRoleVM.getUserId();
		userRepo.updateUserRole(updateUserRoleVM);
		
		updateUserRoleVM = new UpdateUserRoleViewModel(id);
		
		return "UserList.xhtml";
	}
	
	public RoleTypeEnum[] roleTypeEnumValues() {
		return RoleTypeEnum.values();
	}

	public UpdateUserRoleViewModel getUpdateUserRoleVM() {
		return updateUserRoleVM;
	}

	public void setUpdateUserRoleVM(UpdateUserRoleViewModel updateUserRoleVM) {
		this.updateUserRoleVM = updateUserRoleVM;
	}
	
}
