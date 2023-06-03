package fr.isika.cda.entities.esnAbonnement;

import java.util.Date;

public class ESNSubscription {
	
	private Date startDate;
    private Date endDate;
    private boolean isActive;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
