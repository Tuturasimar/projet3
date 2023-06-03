package fr.isika.cda.entities.esnAbonnement;

import java.io.Serializable;


import javax.faces.view.ViewScoped;



@ViewScoped
public class Abonnement implements Serializable {

    private int id;
    private String nom;
    private String duree;
    private String statut;
    private String option;
    private double prix;

    public Abonnement() {
    }

    public Abonnement(int id, String nom, String duree, String statut, String option, double prix) {
        this.id = id;
        this.nom = nom;
        this.duree = duree;
        this.statut = statut;
        this.option = option;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

