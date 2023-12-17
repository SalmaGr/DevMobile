package com.example.graja_salma_mesure_glycemie.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.graja_salma_mesure_glycemie.model.User;

public class LoginController {
    private static LoginController instance;
    private static User user;
    private static final String SHARED_PREFS = "sharedPrefs";

    private LoginController() {
        // Constructor private for Singleton pattern
    }

    public static final LoginController getInstance(Context context){
        if(LoginController.instance ==null){
            LoginController.instance = new LoginController();
        }
        recapUser(context);
        return LoginController.instance;
    }

    public void createUser(String userName, String password, Context context) {
        user = new User(userName, password);

        // Sauvegarde des coordonnées de l'utilisateur dans SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        editor.putString("password", password);
        editor.apply();
    }

    public static void recapUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName","");
        String password = sharedPreferences.getString("password","");
        user = new User(userName,password);
    }
    public String getUserName() {
        return user.getNom();
    }

    public String getPassword() {
        return user.getMdp();
    }
    public User getUser() {
        return user;
    }

}
