package com.bomesmo.huetimer.main.core;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bomesmo.huetimer.main.activities.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lucas on 24/12/2017.
 */

public class Core {

    private MainActivity mainActivity;

    //mainScreen, display, scramble
    private View[] views;
    private ArrayList<Solve> solves;

    public Core(MainActivity mainActivity, ArrayList<Solve> solves, View... views){
        this.mainActivity = mainActivity;
        this.solves = solves;
        this.views = views;

        setupTouchesListener();
    }

    private void setupTouchesListener(){
        views[0].setOnTouchListener(new TouchesListener(this));
    }

    public void setTheScramble(String scramble){
        TextView tv = getScramble();
        tv.setText(scramble);
    }

    public String getTheScramble(){
        TextView tv = getScramble();
        return tv.getText().toString();
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public ArrayList<Solve> getSolves() {
        return solves;
    }

    public RelativeLayout getMainDisplay(){
        RelativeLayout mainDisplay = (RelativeLayout) views[0];
        return mainDisplay;
    }

    public TextView getDisplay(){
        TextView display = (TextView) views[1];
        return display;
    }

    public TextView getScramble(){
        TextView scramble = (TextView) views[2];
        return scramble;
    }
}