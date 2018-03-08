package com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.util.TimerTask;

/**
 * Created by Lucas on 24/12/2017.
 *
 *
 * Classe simples que é responsável por retornar o tempo decorrido do cronômetro,
 * bem como atualizar o texto do display.
 */

public class Chronometer extends TimerTask {

    private TextView target;
    private long i = System.currentTimeMillis();

    public long e;

    public Chronometer(TextView target) {
        this.target = target;
    }

    private long getElapsedTime(){
        e = System.currentTimeMillis() - i;
        return e;
    }

    /**
     * Utiliza-se o método runOnUiThread para alterar algo da view, uma vez que
     * views só podem ser atualizadas na thread principal da atividade.
     */
    @Override
    public void run() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                target.setText(TF.longToTimestamp(getElapsedTime()));
            }
        });
    }
}