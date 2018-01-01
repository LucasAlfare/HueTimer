package com.bomesmo.huetimer.main.core;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.bomesmo.huetimer.main.auxiliar.Chronometer;
import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.auxiliar.SolvesHandler;
import com.bomesmo.huetimer.main.scrambles.Scramble;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lucas on 24/12/2017.
 */

public class MainTouchesHandler implements View.OnTouchListener {

    private Core core;
    private Timer timerInsp, timerLong, timer;
    private Chronometer chronometer;

    private boolean isInspecting, isLong, isTimming;
    private String scrambleShown;

    MainTouchesHandler(Core core) {
        this.core = core;
    }

    private void startInsp(){
        timerInsp = new Timer();
        timerInsp.scheduleAtFixedRate(new TimerTask() {
            byte count = 15;

            @Override
            public void run() {
                core.getMainActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        core.getDisplay().setText(count-- + "");
                    }
                });
            }
        }, 0, 1000);

        isInspecting = true;
    }

    private void stopInsp(){
        timerInsp.cancel();
        timerInsp = null;
        isInspecting = false;
    }

    private void start(){
        for (int i = 2; i < core.getViews().length; i++){
            core.getViews()[i].setEnabled(false);
        }

        timer = new Timer();
        chronometer = new Chronometer(core.getMainActivity(), core.getDisplay());
        timer.scheduleAtFixedRate(chronometer, 0, 51);
        isTimming = true;
        isInspecting = false;
    }

    private void stop(){
        for (int i = 2; i < core.getViews().length; i++){
            core.getViews()[i].setEnabled(true);
        }

        timer.cancel();
        timer = null;
        isInspecting = false;
        isTimming = false;

        String scrambleSequence = Scramble.getScrambleByID(core.getScrambleID());
        core.setScrambleShown(scrambleSequence);

        Solve s = new Solve(chronometer.e, scrambleShown);
        SolvesHandler.addSolve(core.getMainActivity().getApplicationContext(), s);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            scrambleShown = core.getScrambleShown();

            if (core.getSetInsp().isChecked()){ //inspectionEnabled
                if (!isInspecting && !isTimming){
                    startInsp();
                } else if (isInspecting && !isTimming){
                    core.getDisplay().setTextSize(45f);
                    core.getDisplay().setTextColor(Color.YELLOW);

                    if (timerLong == null){
                        timerLong = new Timer();

                        timerLong.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                isLong = true;
                                core.getMainActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        core.getDisplay().setTextColor(Color.GREEN);
                                    }
                                });
                            }
                        }, 300, 1000);
                    }
                }

                if (!isInspecting && isTimming){
                    stop();
                }
            } else {
                if (!isTimming){
                    core.getDisplay().setTextSize(45f);
                    core.getDisplay().setTextColor(Color.YELLOW);

                    if (timerLong == null){
                        timerLong = new Timer();

                        timerLong.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                isLong = true;
                                core.getMainActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        core.getDisplay().setTextColor(Color.GREEN);
                                    }
                                });
                            }
                        }, 300, 1000);
                    }
                } else {
                    stop();
                }
            }

            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP){
            core.getDisplay().setTextSize(55f);
            core.getDisplay().setTextColor(Color.BLACK);

            if (isLong && !isTimming){
                if (core.getSetInsp().isChecked()) stopInsp(); //inspectionEnabled
                start();
            }

            isLong = false;

            if (timerLong != null){
                timerLong.cancel();
                timerLong = null;
            }

            return true;
        }

        return false;
    }
}
