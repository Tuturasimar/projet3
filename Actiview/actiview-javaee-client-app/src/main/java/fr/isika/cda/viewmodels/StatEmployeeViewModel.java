package fr.isika.cda.viewmodels;

import java.time.LocalDate;
import java.util.List;

import fr.isika.cda.entities.ar.ActivityDate;

public final class StatEmployeeViewModel {

	private Long arId;
	private LocalDate arCreatedAt;

	private int missionHours;
	private int formationHours;
	private int absenceHours;
	private int totalHours;

	private List<ActivityDate> allActivityDates;

	// listes une fois triées par type d'activité
	private List<ActivityDate> missionActivityDates;
	private List<ActivityDate> formationActivityDates;
	private List<ActivityDate> absenceActivityDates;

	// Getters & setters
	public Long getArId() {
		return arId;
	}

	public void setArId(Long arId) {
		this.arId = arId;
	}

	public LocalDate getArCreatedAt() {
		return arCreatedAt;
	}

	public void setArCreatedAt(LocalDate arCreatedAt) {
		this.arCreatedAt = arCreatedAt;
	}

	public List<ActivityDate> getAllActivityDates() {
		return allActivityDates;
	}

	public void setAllActivityDates(List<ActivityDate> allAcitivityDates) {
		this.allActivityDates = allAcitivityDates;
	}

	public List<ActivityDate> getMissionActivityDates() {
		return missionActivityDates;
	}

	public void setMissionActivityDates(List<ActivityDate> missionActivityDates) {
		this.missionActivityDates = missionActivityDates;
	}

	public List<ActivityDate> getFormationActivityDates() {
		return formationActivityDates;
	}

	public void setFormationActivityDates(List<ActivityDate> formationActivityDates) {
		this.formationActivityDates = formationActivityDates;
	}

	public List<ActivityDate> getAbsenceActivityDates() {
		return absenceActivityDates;
	}

	public void setAbsenceActivityDates(List<ActivityDate> absenceActivityDates) {
		this.absenceActivityDates = absenceActivityDates;
	}

	public int getMissionHours() {
		return missionHours;
	}

	public void setMissionHours(int missionHours) {
		this.missionHours = missionHours;
	}

	public int getFormationHours() {
		return formationHours;
	}

	public void setFormationHours(int formationHours) {
		this.formationHours = formationHours;
	}

	public int getAbsenceHours() {
		return absenceHours;
	}

	public void setAbsenceHours(int absenceHours) {
		this.absenceHours = absenceHours;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}
	
}
