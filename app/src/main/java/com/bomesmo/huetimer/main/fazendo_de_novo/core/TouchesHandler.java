package com.bomesmo.huetimer.main.fazendo_de_novo.core;

import android.view.MotionEvent;
import android.view.View;

import com.bomesmo.huetimer.main.MyApp;
import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo.MainActivity2;
import com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar.AdapterNovo;
import com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar.Chronometer;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Read;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Update;
import com.bomesmo.huetimer.main.fazendo_de_novo.fragments.SolvesFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.UUID;

/**
 * Created by Lucas on 01/03/2018.
 */
public class TouchesHandler implements View.OnTouchListener {

    private Core core;
    private Timer timerInsp, timerLong, timer;
    private Chronometer chronometer;

    private Timer t;
    private int numPhases = MainActivity2.numPhases;
    private int phasesCouter = 0;
    private ArrayList<Long> phasesTimes;
    private ArrayList<Solve> solves;

    private boolean isInspecting, isLong, isTimming;
    private String scrambleShown;

    public TouchesHandler(Core core) {
        this.core = core;

        phasesTimes = new ArrayList<>();
        solves = new ArrayList<>();
    }

    private void startTimer(){
        showHideUI(true);
        if (phasesCouter < numPhases){
            if (t == null){
                t = new Timer();
            } else {
                phasesTimes.add(chronometer.e);
                t.cancel();
                t = new Timer();
            }

            chronometer = new Chronometer(getCore().getDisplay());
            t.scheduleAtFixedRate(chronometer, 0, 51);
            phasesCouter++;
        } else {
            phasesTimes.add(chronometer.e);
            stopTimer();
            phasesTimes = new ArrayList<>();
        }

        getCore().getCurrPhaseView().setText("current phase: " + phasesCouter);
    }

    private void showHideUI(boolean hide){
        int visibility = hide ? View.INVISIBLE : View.VISIBLE;
        for (View x : getCore().getViews()){
            x.setVisibility(visibility);
        }

        getCore().getMainScreen().setVisibility(View.VISIBLE);
        getCore().getDisplay().setVisibility(View.VISIBLE);
        getCore().getCurrPhaseView().setVisibility(View.VISIBLE);
    }

    private void stopTimer(){
        t.cancel();
        t = null;
        phasesCouter = 0;

        showHideUI(false);

        new Update().addSolve(new Solve(UUID.randomUUID(), phasesTimes, getCore().getScrambleShown(), false, false));
        getCore().getRadioGroup().check(R.id.radioOk);//coloca o botão ok clicado novamente

        SolvesFragment.animatedListView.setAdapter(new AdapterNovo(MyApp.getContext()));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP){
            startTimer();

            return true;
        } else if (event.getAction() == MotionEvent.ACTION_DOWN){
            //getCore().getDisplay().setText("DOWN");
            return true;
        }

        return false;
    }

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }
}
