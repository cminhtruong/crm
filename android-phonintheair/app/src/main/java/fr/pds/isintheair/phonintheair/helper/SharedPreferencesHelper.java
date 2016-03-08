package fr.pds.isintheair.phonintheair.helper;

import android.content.SharedPreferences;

import fr.pds.isintheair.phonintheair.PhonintheairApp;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class SharedPreferencesHelper {
    public static Integer readInteger(String key, Integer value) {
        SharedPreferences sharedPreferences = PhonintheairApp.context.getSharedPreferences("pref", 0);

        return sharedPreferences.getInt(key, value);
    }

    public static void writeInteger(String key, Integer value) {
        SharedPreferences        sharedPreferences = PhonintheairApp.context.getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor            = sharedPreferences.edit();

        editor.putInt(key, value);
        editor.apply();
    }

    public static String readString(String key, String value) {
        SharedPreferences sharedPreferences = PhonintheairApp.context.getSharedPreferences("pref", 0);

        return sharedPreferences.getString(key, value);
    }

    public static void writeString(String key, String value) {
        SharedPreferences        sharedPreferences = PhonintheairApp.context.getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor            = sharedPreferences.edit();

        editor.putString(key, value);
        editor.apply();
    }
}
