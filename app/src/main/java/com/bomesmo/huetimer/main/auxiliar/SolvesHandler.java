package com.bomesmo.huetimer.main.auxiliar;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Lucas on 27/12/2017.
 */

public class SolvesHandler {

    /**
     * Retorna a lista de solves armazenada na memória do celular.
     *
     * @param context contexto no qual a recuperação de dados irá ocorrer.
     * @return {@ArrayList<Solve>} contendo as solves armazenadas na memória.
     */
    public static ArrayList<Solve> getSolves(Context context){
        return PreferencesHelper.dataContains(context, "solves") ? PreferencesHelper.getSolvesList(context, "solves") : new ArrayList<Solve>();
    }

    /**
     * Adiciona um objeto Solve às solves salvas na memória do celular.
     *
     * @param context contexto no qual a ação irá ocorrer.
     * @param x objeto Solve a ser guardado.
     */
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

    /**
     * Remove uma solve da memória do celular de um índice específico.
     *
     * @param context contexto no qual a ação irá ocorrer.
     * @param solveIndex índice do elemento a ser removido/deletado.
     */
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

    /**
     * Altera uma solve de um índice específico guardada na memória do aparelho
     * pelo parâmetro repassado.
     *
     * @param context contexto no qual a ação irá ocorrer.
     * @param targetIndex índice do elemento a ser editado.
     * @param newValue novo objeto.
     */
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
