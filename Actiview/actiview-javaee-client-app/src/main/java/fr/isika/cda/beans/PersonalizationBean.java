package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.primefaces.component.colorpicker.ColorPicker;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.repository.ColorConfigRepository;
import fr.isika.cda.repository.ImageConfigRepository;
import fr.isika.cda.utils.FileUploadUtils;
import fr.isika.cda.viewmodels.ColorConfigViewModel;
import fr.isika.cda.viewmodels.ImageConfigViewModel;

@ManagedBean
@SessionScoped
public class PersonalizationBean {

	@Inject
	private ImageConfigRepository imgConfigRepo;
	
	@Inject
	private ColorConfigRepository colorConfigRepo;

	private ImageConfigViewModel imgConfigVm = new ImageConfigViewModel();
	private ColorConfigViewModel colorConfigVm = new ColorConfigViewModel();
	
	private UploadedFile logo;
	private UploadedFile banner;
	
	private String colorBackground;
	
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
	
	public String saveColor() {
		colorConfigRepo.save(colorConfigVm);
		colorConfigVm = new ColorConfigViewModel();
		return "adminPersonalizationColor.xhtml";
	}
	
//	public String onPopupClosed(AjaxBehaviorEvent e) {
//		ColorPicker picker = (ColorPicker) e.getComponent();
//		String colorName = (String) picker.getValue();
//		
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Popup closed: " + picker.getValue(), null);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        return colorName;
//	}
	
    public void onColorChange(AjaxBehaviorEvent e) {
        ColorPicker picker = (ColorPicker) e.getComponent();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Color changed: " + picker.getValue(), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public String getColorBackground() {
		return colorBackground;
	}

	public void setColorBackground(String colorBackground) {
		this.colorBackground = colorBackground;
	}
	
	
}
