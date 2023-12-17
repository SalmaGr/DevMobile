package com.example.graja_salma_mesure_glycemie.controller;

import com.example.graja_salma_mesure_glycemie.model.Patient;

public class Controller {
    private static Controller instance = null;
    private static Patient patient;

    public Controller()
    {
        super();
    }
    public static final Controller getInstance(){
        if(Controller.instance == null)
            Controller.instance = new Controller();
        return Controller.instance;
    }
    //Flèche "Update" Controller --> Model
    public void createPatient(int age, float valeurMesuree, boolean isFasting){
        patient = new Patient(age, valeurMesuree, isFasting);
    }
    //Flèche "Notify" Model --> Controller
    public String getReponse() {
        return patient.getReponse();
    }
    public static Patient getPatient() {
        return patient;
    }
}