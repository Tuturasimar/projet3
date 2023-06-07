package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.primefaces.component.colorpicker.ColorPicker;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import fr.isika.cda.repository.ColorConfigRepository;
import fr.isika.cda.repository.FontConfigRepository;
import fr.isika.cda.repository.ImageConfigRepository;
import fr.isika.cda.repository.UserRepository;
import fr.isika.cda.utils.FileUploadUtils;
import fr.isika.cda.viewmodels.ColorConfigViewModel;
import fr.isika.cda.viewmodels.FontConfigViewModel;
import fr.isika.cda.viewmodels.ImageConfigViewModel;

@ManagedBean
@SessionScoped
public class PersonalizationBean {

	@Inject
	private ImageConfigRepository imgConfigRepo;

	@Inject
	private ColorConfigRepository colorConfigRepo;
	
	@Inject
	private UserRepository userRepo;
	
	@Inject
	private FontConfigRepository fontConfigRepo;


	private ImageConfigViewModel imgConfigVm = new ImageConfigViewModel();
	private ColorConfigViewModel colorConfigVm = new ColorConfigViewModel();
	private FontConfigViewModel fontConfigVm = new FontConfigViewModel();
	
	private UploadedFile logo;
	private UploadedFile banner;

	private String colorBackground;
	private String colorTitle;
	private String colorButton;
	private String colorText;
	
	private String font;

	private String template;

	public String save() {

		Long companyId = userRepo.findCompanyByUserConnected().getId();
		imgConfigVm.setCompanyId(companyId);
		imgConfigRepo.update(imgConfigVm);
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
		Long companyId = userRepo.findCompanyByUserConnected().getId();
		colorConfigVm.setCompanyId(companyId);
		colorConfigRepo.update(colorConfigVm);
		
		fontConfigVm.setCompanyId(companyId);
		fontConfigRepo.update(fontConfigVm);
		
		fontConfigVm = new FontConfigViewModel();
		colorConfigVm = new ColorConfigViewModel();
		return "TestCustomizeColor.xhtml";
	}

	public void saveColorTitle(AjaxBehaviorEvent e) {
		String color = onColorChange(e);
		colorConfigVm.setTitleColor(color);
	}

	public void saveColorButton(AjaxBehaviorEvent e) {
		String color = onColorChange(e);
		colorConfigVm.setButtonColor(color);
	}

	public void saveColorText(AjaxBehaviorEvent e) {
		String color = onColorChange(e);
		colorConfigVm.setTextColor(color);
	}

	public void saveColorBackground(AjaxBehaviorEvent e) {
		String color = onColorChange(e);
		colorConfigVm.setBackgroundColor(color);
		System.out.println(color);
	}
	
	public void saveTemplateChoice(AjaxBehaviorEvent e) {
		String templateChoice = onTemplateChoice(e);
		imgConfigVm.setTemplateChoice(templateChoice);
	}
	
	public void saveFontChoice(AjaxBehaviorEvent e) {
		String fontChoice = onFontChoice(e);
		fontConfigVm.setFontFamily(fontChoice);
	}

	public String onColorChange(AjaxBehaviorEvent e) {
		ColorPicker picker = (ColorPicker) e.getComponent();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Color changed: " + picker.getValue(), null);

		String color = (String) picker.getValue();
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return color;
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
	
	public String onTemplateChoice(AjaxBehaviorEvent e) {
		UIInput input = (UIInput) e.getSource();
		String templateChoice = input.getValue().toString();
		
		FacesMessage message = new FacesMessage("Successful choice template", templateChoice);
		FacesContext.getCurrentInstance().addMessage(null, message);

		return templateChoice;
	}
	
	public String onFontChoice(AjaxBehaviorEvent e) {
		UIInput input = (UIInput) e.getSource();
		String fontChoice = input.getValue().toString();
		
		FacesMessage message = new FacesMessage("Successful choice font", fontChoice);
		FacesContext.getCurrentInstance().addMessage(null, message);

		return fontChoice;
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

	public String getColorTitle() {
		return colorTitle;
	}

	public void setColorTitle(String colorTitle) {
		this.colorTitle = colorTitle;
	}

	public String getColorButton() {
		return colorButton;
	}

	public void setColorButton(String colorButton) {
		this.colorButton = colorButton;
	}

	public String getColorText() {
		return colorText;
	}

	public void setColorText(String colorText) {
		this.colorText = colorText;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public FontConfigViewModel getFontConfigVm() {
		return fontConfigVm;
	}

	public void setFontConfigVm(FontConfigViewModel fontConfigVm) {
		this.fontConfigVm = fontConfigVm;
	}


	

	
}
