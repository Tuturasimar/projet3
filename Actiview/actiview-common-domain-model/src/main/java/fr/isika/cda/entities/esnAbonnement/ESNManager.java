package fr.isika.cda.entities.esnAbonnement;

import java.util.Date;

public class ESNManager {
	
	private ESN esn;
    private ESNSubscription subscription;

    public ESNManager(ESN esn) {
        this.esn = esn;
        this.subscription = new ESNSubscription();
    }

    public void startSubscription(Date startDate, Date endDate) {
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        subscription.setActive(true);
    }

    public void cancelSubscription() {
        subscription.setActive(false);
    }

}
