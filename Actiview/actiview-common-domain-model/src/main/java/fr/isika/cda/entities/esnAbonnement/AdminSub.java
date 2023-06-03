package fr.isika.cda.entities.esnAbonnement;

public class AdminSub {
	
	public void cancelESNSubscription(ESN esn) {
        ESNManager esnManager = new ESNManager(esn);
        esnManager.cancelSubscription();
    }
	
	 public void changeOption(ESN esn, String newOption) {
	        esn.setOption(newOption);
	    }

}
