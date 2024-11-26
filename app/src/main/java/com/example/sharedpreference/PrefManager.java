package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREFS_FILENAME = "AuthAppPrefs"; // Nama file SharedPreferences tempat data disimpan.
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn"; // Kunci untuk menyimpan status login pengguna.

    private static final  String KEY_USERNAME = "username"; // Kunci untuk menyimpan data username dan password.
    private static final String KEY_PASSWORD = "password";

    private final SharedPreferences sharedPreferences;

    private static volatile PrefManager instance;

    private PrefManager (Context context){
        sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
    }
    public static PrefManager getInstance(Context context){ // Metode untuk mendapatkan instance PrefManager
        if (instance == null){
            synchronized (PrefManager.class){
                if (instance == null){
                    instance = new PrefManager(context.getApplicationContext());}
            }
        }
        return instance;
    }

    public void saveUsername (String username){ // Metode untuk menyimpan username ke SharedPreferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void savePassword (String pwd){ // Metode untuk menyimpan password ke SharedPreferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASSWORD, pwd);
        editor.apply();
    }

    public String getUsername () { // Metode untuk mendapatkan username dari SharedPreferences.
        return sharedPreferences.getString(KEY_USERNAME,"");
    }

    public String getPassword () { // Metode untuk mendapatkan password dari SharedPreferences.
        return sharedPreferences.getString(KEY_PASSWORD,"");
    }

    public void setLoggedIn (boolean isLoggedIn){ // Metode untuk menyimpan status login pengguna.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn(){ // Metode untuk mendapatkan status login pengguna.
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clear(){ // Metode untuk menghapus semua data yang disimpan di SharedPreferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
