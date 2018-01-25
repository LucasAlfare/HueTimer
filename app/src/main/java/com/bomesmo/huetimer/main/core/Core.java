package com.bomesmo.huetimer.main.core;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bomesmo.huetimer.main.activities.MainActivity;

/**
 * Created by Lucas on 24/12/2017.
 */

public class Core {

    private MainActivity mainActivity;

    //mainScreen, display, toggleScramble, floatingActionButton, setInsp, fab2
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

    String getScrambleShown(){
        TextView tv = getScrambleView();
        return tv.getText().toString();
    }

    MainActivity getMainActivity() {
        return mainActivity;
    }

    public RelativeLayout getMainDisplay(){
        return (RelativeLayout) views[0];
    }

    TextView getDisplay(){
        return (TextView) views[1];
    }

    TextView getScrambleView(){
        return (TextView) views[2];
    }

    CheckBox getSetInsp(){
        return (CheckBox) views[4];
    }

    View[] getViews() {
        return views;
    }

    public int getScrambleID() {
        return scrambleID;
    }

    public void setScrambleID(int scrambleID) {
        this.scrambleID = scrambleID;
    }
}