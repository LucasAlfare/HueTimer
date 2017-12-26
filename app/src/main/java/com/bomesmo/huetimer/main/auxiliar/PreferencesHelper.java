package com.bomesmo.huetimer.main.auxiliar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bomesmo.huetimer.main.core.Solve;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Lucas on 26/12/2017.
 */
//PreferencesHelper
public class PreferencesHelper {
    private static final int SALVAR = 1;
    private static final int OBTER = 2;
    public static final int REMOVER = 3;

    public static ArrayList<Solve> getSolvesList(Context context, String key){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json;

        json = sharedPrefs.getString(key, "");
        Type type = new TypeToken<ArrayList<Solve>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static ArrayList<Solve> handleSolveList(Context context, String key, ArrayList<Solve> value) {
        return handleSolveList(context, SALVAR, key, value);
    }

    public static ArrayList<Solve> handleSolveList(Context context, String key) {
        return handleSolveList(context, OBTER, key, null);
    }

    public static ArrayList<Solve> handleSolveList(Context context, int action, String key, ArrayList<Solve> value){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json;

        switch (action){
            case SALVAR:
                json = gson.toJson(value);
                editor.putString(key, json);
                editor.apply();
                editor.commit();
                return null;
            case OBTER:
                json = sharedPrefs.getString(key, "");
                Type type = new TypeToken<ArrayList<Solve>>() {}.getType();
                ArrayList<Solve> arrayList = gson.fromJson(json, type);
                return arrayList;
            case REMOVER:
                editor.remove(key);
        }
        return null;
    }


    public static ArrayList<String> handleStringList(Context contexto, String key) {
        return handleStringList(contexto, OBTER, key, null);
    }

    public static void handleStringList(Context contexto, String key, ArrayList<String> value) {
        handleStringList(contexto, SALVAR, key, value);
    }

    public static ArrayList<String> handleStringList(Context contexto, int action, String key, ArrayList<String> value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor editor;
        int valueSize;

        switch (action){
            case SALVAR:
                editor = preferences.edit();
                valueSize = value.size();
                editor.putInt(key + "_size", valueSize);

                for (int i = 0; i < valueSize; i++){
                    editor.putString(key + i, value.get(i));
                }

                editor.apply();
                editor.commit();
                return null;

            case OBTER:
                valueSize = preferences.getInt(key + "_size", 0);

                ArrayList<String> r = new ArrayList<>();
                for (int i = 0; i < valueSize; i++){
                    r.add(preferences.getString(key + i, ":("));
                }

                return r;

            case REMOVER:
                editor = preferences.edit();
                valueSize = preferences.getInt(key + "_size", 0);

                for (int i = 0; i < valueSize; i++){
                    editor.remove(key + i);
                }

                editor.remove(key + "_size");
        }

        return null;
    }

    public static boolean handleBoolean(Context contexto, String key) {
        return handleBoolean(contexto, OBTER, key, false);
    }

    public static boolean handleBoolean(Context contexto, String key, boolean value) {
        return handleBoolean(contexto, SALVAR, key, value);
    }

    public static boolean handleBoolean(Context contexto, int action, String key, boolean value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor editor;

        switch (action){
            case SALVAR:
                editor = preferences.edit();
                editor.putBoolean(key, value);
                editor.apply();
                return editor.commit();

            case OBTER:
                return preferences.getBoolean(key, false);

            case REMOVER:
                editor = preferences.edit();
                editor.remove(key);
                editor.apply();
                return editor.commit();
        }

        return false;
    }

    public static boolean dataContains(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
    }

    public static boolean clearAllData(Context context){
        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferenceManager.edit();
        editor.clear();
        editor.apply();
        return editor.commit();
    }
}