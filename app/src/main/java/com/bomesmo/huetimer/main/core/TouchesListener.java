package com.bomesmo.huetimer.main.core;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.bomesmo.huetimer.main.auxiliar.Chronometer;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lucas on 24/12/2017.
 */

public class TouchesListener implements View.OnTouchListener {

    private Core core;
    private Timer timer, timerLong;
    private Chronometer chronometer;
    private String scrambleShown;

    private boolean isLong, started;

    public TouchesListener(Core core) {
        this.core = core;
    }

    private void start() {
        timer = new Timer();
        chronometer = new Chronometer(core.getMainActivity(), core.getDisplay());
        timer.scheduleAtFixedRate(chronometer, 0, 51);
        started = true;
    }

    private void stop() {
        timer.cancel();
        timer = null;
        started = false;

        //TODO: scramble correto
        String scrambleSequence = new Random().nextInt() + " kkkkkksahfuhsadf";
        core.setTheScramble(scrambleSequence);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            scrambleShown = core.getTheScramble();
            core.getDisplay().setTextSize(60f);
            core.getDisplay().setTextColor(Color.YELLOW);

            if (timerLong == null) {
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

            if (started) {
                //TODO: ADICIONAR SOLVES AQUI
                //core.getSolves().add(new Solve(chronometer.e, scrambleShown));
                stop();
            }

            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            core.getDisplay().setTextSize(80f);
            core.getDisplay().setTextColor(Color.BLACK);
            if (isLong) {
                start();
            }

            isLong = false;
            timerLong.cancel();
            timerLong = null;

            return true;
        }

        return false;
    }
}
