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

    private int scrambleID;

    public Core(MainActivity mainActivity, View... views){
        this.mainActivity = mainActivity;
        this.views = views;

        setupTouchesListener();
    }

    private void setupTouchesListener(){
        views[0].setOnTouchListener(new MainTouchesHandler(this));
    }

    public void setScrambleShown(String scramble){
        TextView tv = getScrambleView();
        tv.setText(scramble);
    }

    public String getScrambleShown(){
        TextView tv = getScrambleView();
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

    public TextView getScrambleView(){
        TextView scramble = (TextView) views[2];
        return scramble;
    }

    public View[] getViews() {
        return views;
    }

    public int getScrambleID() {
        return scrambleID;
    }

    public void setScrambleID(int scrambleID) {
        this.scrambleID = scrambleID;
    }
}