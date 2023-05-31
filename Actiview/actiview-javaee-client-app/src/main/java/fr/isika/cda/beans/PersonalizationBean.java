package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.repository.ImageConfigRepository;
import fr.isika.cda.utils.FileUploadUtils;
import fr.isika.cda.viewmodels.ImageConfigViewModel;

@ManagedBean
@SessionScoped
public class PersonalizationBean {

	@Inject
	private ImageConfigRepository imgConfigRepo;

	private ImageConfigViewModel imgConfigVm = new ImageConfigViewModel();
	
	private UploadedFile logo;
	private UploadedFile banner;
	
	public String save() {
		imgConfigRepo.save(imgConfigVm);
		imgConfigVm = new ImageConfigViewModel();
		return "adminPersonalizationPreview.xhtml";
	}
	
	public void uploadLogo(FileUploadEvent e) {
		String fileName = uploadFile(e);
		// Mettre à jour le vm
		imgConfigVm.setLogo(fileName);
	}
	public void uploadBanner(FileUploadEvent e) {
		String fileName = uploadFile(e);
		// Mettre à jour le vm
		imgConfigVm.setBanner(fileName);
	}

	public UploadedFile getBanner() {
		return banner;
	}
	public void setBanner(UploadedFile banner) {
		this.banner = banner;
	}
	public UploadedFile getLogo() {
		return logo;
	}
	public void setLogo(UploadedFile logo) {
		this.logo = logo;
	}

	private String uploadFile(FileUploadEvent e) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

		UploadedFile uploadedFile = e.getFile();
		String fileName = String.join("_", timestamp, uploadedFile.getFileName());

		// Ecrire le fichier physiquement qq part
		FileUploadUtils.uploadFileToApp(uploadedFile, fileName);
		
		FacesMessage message = new FacesMessage("Successful file upload", uploadedFile.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return fileName;
	}
	
	
}
