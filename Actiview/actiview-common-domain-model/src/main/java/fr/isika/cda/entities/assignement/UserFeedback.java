package fr.isika.cda.entities.assignement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
    private String comment;
    private int gradeMission;
    private int gradeManager;
    private int gradeClientRelation;
    private int gradeUserComfort;
    
    public long getId() {
		return id;
	}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGradeMission() {
        return gradeMission;
    }

    public void setGradeMission(int gradeMission) {
        this.gradeMission = gradeMission;
    }

    public int getGradeManager() {
        return gradeManager;
    }

    public void setGradeManager(int gradeManager) {
        this.gradeManager = gradeManager;
    }

    public int getGradeClientRelation() {
        return gradeClientRelation;
    }

    public void setGradeClientRelation(int gradeClientRelation) {
        this.gradeClientRelation = gradeClientRelation;
    }

    public int getGradeUserComfort() {
        return gradeUserComfort;
    }

    public void setGradeUserComfort(int gradeUserComfort) {
        this.gradeUserComfort = gradeUserComfort;
    }
}
