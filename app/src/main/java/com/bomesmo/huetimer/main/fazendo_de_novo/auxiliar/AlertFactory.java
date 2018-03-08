package com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lucas on 07/03/2018.
 */

public class AlertFactory {

    public static AlertDialog.Builder newAlert(Context context, String message, String tittle, int layoutResource) {
        return newAlert(context, message, tittle, layoutResource, null);
    }

    public static AlertDialog.Builder newAlert(Context context, String message, String tittle, int layoutResource, ViewGroup parent){
        AlertDialog.Builder r = new AlertDialog.Builder(context);
        r.setTitle(tittle);
        r.setMessage(message);

        View targetView = LayoutInflater.from(context).inflate(layoutResource, parent, false);

        r.setView(targetView);

        return r;
    }
}
