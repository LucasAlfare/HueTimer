package com.bomesmo.huetimer.main.auxiliar;

import android.content.Context;

import com.bomesmo.huetimer.main.core.Solve;

import java.util.ArrayList;

/**
 * Created by Lucas on 27/12/2017.
 */

public class SolvesHandler {

    public static ArrayList<Solve> getSolves(Context context){
        return PreferencesHelper.getSolvesList(context, "solves");
    }

    public static void addSolve(Context context, Solve x){
        ArrayList<Solve> saved;

        if (PreferencesHelper.dataContains(context, "solves")){
            saved = PreferencesHelper.getSolvesList(context, "solves");
            PreferencesHelper.remove(context, "solves");
        } else {
            saved = new ArrayList<>();
        }

        saved.add(x);
        PreferencesHelper.add(context, "solves", saved);
    }

    public static void removeSolve(Context context, int solveIndex){
        ArrayList<Solve> saved;

        if (PreferencesHelper.dataContains(context, "solves")){
            saved = PreferencesHelper.getSolvesList(context, "solves");
            if (saved.size() > 0){
                saved.remove(solveIndex);
                PreferencesHelper.remove(context, "solves");
                PreferencesHelper.add(context, "solves", saved);
            }
        }
    }

    public static void setSolve(Context context, int targetIndex, Solve newValue){
        ArrayList<Solve> saved;

        if (PreferencesHelper.dataContains(context, "solves")){
            saved = PreferencesHelper.getSolvesList(context, "solves");
            if (saved.size() > 0){
                saved.set(targetIndex, newValue);
                PreferencesHelper.remove(context, "solves");
                PreferencesHelper.add(context, "solves", saved);
            }
        }
    }
}
