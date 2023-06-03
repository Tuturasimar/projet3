package fr.isika.cda.entities.esnAbonnement;

public class ESN {
	
	private String id;
    private String name;
    private String option;
    private boolean actif;
	
	
    

	public boolean isActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}


	public ESN(String nom, boolean actif) {
    this.name = nom;
    this.actif = actif;
	}
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
