package com.bomesmo.huetimer.main.auxiliar;

import android.widget.TextView;

import com.bomesmo.huetimer.main.activities.MainActivity;

import java.util.TimerTask;

/**
 * Created by Lucas on 24/12/2017.
 *
 *
 * Classe simples que é responsável por retornar o tempo decorrido do cronômetro,
 * bem como atualizar o texto do display.
 */

public class Chronometer extends TimerTask {

    private MainActivity mainActivity;
    private TextView target;
    private long i = System.currentTimeMillis();

    public long e;

    public Chronometer(MainActivity mainActivity, TextView target) {
        this.mainActivity = mainActivity;
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
        mainActivity.runOnUiThread(new Runnable(){
            @Override
            public void run(){
                target.setText(TF.longToTimestamp(getElapsedTime()));
            }
        });
    }
}
