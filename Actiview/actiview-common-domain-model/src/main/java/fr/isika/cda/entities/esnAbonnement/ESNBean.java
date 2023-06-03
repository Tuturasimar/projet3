package fr.isika.cda.entities.esnAbonnement;

import java.util.ArrayList;
import java.util.List;






public class ESNBean {
    private List<ESN> esns;
    

    public void init() {
       
        esns = new ArrayList<>();
        esns.add(new ESN("ESN 1", true));
        esns.add(new ESN("ESN 2", true));
        esns.add(new ESN("ESN 3", false));
        esns.add(new ESN("ESN 4", false));
        esns.add(new ESN("ESN 5", true));
        esns.add(new ESN("ESN 6", false));
    }
    
   
    public void activerESN(ESN esn) {
        esn.setActif(true);
    }
    
    public void desactiverESN(ESN esn) {
        esn.setActif(false);
    }
    
    // Getter pour la liste des ESN
    public List<ESN> getEsns() {
        return esns;
    }
}
