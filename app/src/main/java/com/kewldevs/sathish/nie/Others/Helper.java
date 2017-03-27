package com.kewldevs.sathish.nie.Others;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kewldevs.sathish.nie.Activities.LoginActivity;
import com.kewldevs.sathish.nie.Activities.MainActivity;

/**
 * Created by sathish on 3/23/17.
 */

public class Helper {

    public static String TAG = "NIE";
    public static String ADMIN = "admin", PASSWORD = "pass";
    public static String SELECTED_COLOR = "#f44336",CLEAR_COLOR = "#b4ebf2";
    public static String Is_LOGGED_IN_KEY = "logged_in";
    public static String USR_KEY = "username";
    public static String USR_PASS = "password";

    public static void Login(Activity a)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean(Helper.Is_LOGGED_IN_KEY, true).apply();
        a.finish();
        a.startActivity(new Intent(a.getApplicationContext(),MainActivity.class));
    }

    public static void Logout(Activity a)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean(Helper.Is_LOGGED_IN_KEY, false).apply();
        a.finish();
        a.startActivity(new Intent(a.getApplicationContext(),LoginActivity.class));

    }


}
