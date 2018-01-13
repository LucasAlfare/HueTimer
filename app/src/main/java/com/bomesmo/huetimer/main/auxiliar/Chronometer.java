package com.bomesmo.huetimer.main.auxiliar;

import android.widget.TextView;

import com.bomesmo.huetimer.main.activities.MainActivity;

import java.util.TimerTask;

/**
 * Created by Lucas on 24/12/2017.
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

    public long getElapsedTime(){
        e = System.currentTimeMillis() - i;
        return e;
    }

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
