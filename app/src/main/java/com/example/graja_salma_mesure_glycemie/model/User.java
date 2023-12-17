package com.example.graja_salma_mesure_glycemie.model;

public class User {
    private  String nom;
    private String mdp;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public User(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }
}
