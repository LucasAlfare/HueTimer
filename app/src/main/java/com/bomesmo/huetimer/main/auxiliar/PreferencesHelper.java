package com.bomesmo.huetimer.main.auxiliar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Lucas on 26/12/2017.
 */
public class PreferencesHelper {

    public static void add(Context context, String key, ArrayList<Solve> x){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(x);
        editor.putString(key, json);
        editor.apply();
        editor.commit();
    }

    public static ArrayList<Solve> getSolvesList(Context context, String key){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json;
        json = sharedPrefs.getString(key, "");
        Type type = new TypeToken<ArrayList<Solve>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static void remove(Context context, String key){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(key);
        editor.apply();
        editor.commit();
    }

    public static void clearAllSolvesData(Context context){
        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferenceManager.edit();
        editor.remove("solves");
        editor.apply();
        editor.commit();
    }

    public static boolean dataContains(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
    }
}

