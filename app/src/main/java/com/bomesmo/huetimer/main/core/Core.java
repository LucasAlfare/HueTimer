package com.bomesmo.huetimer.main.core;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bomesmo.huetimer.main.activities.MainActivity;

/**
 * Created by Lucas on 24/12/2017.
 */

public class Core {

    private MainActivity mainActivity;

    //mainScreen, display, scramble
    private View[] views;

    public Core(MainActivity mainActivity, View... views){
        this.mainActivity = mainActivity;
        this.views = views;

        setupTouchesListener();
    }

    private void setupTouchesListener(){
        views[0].setOnTouchListener(new MainTouchesHandler(this));
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