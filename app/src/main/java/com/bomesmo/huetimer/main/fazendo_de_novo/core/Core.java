package com.bomesmo.huetimer.main.fazendo_de_novo.core;

/**
 * Created by Lucas on 04/03/2018.
 */

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bomesmo.huetimer.main.fazendo_de_novo.MainActivity2;

public class Core {

    private MainActivity2 mainActivity2;

    //mainScreen, display, scramble, toggleInsp, floatingActionButton, fab2, radioGroup
    private View[] views;

    private int scrambleID;

    public Core(View... views){
        this.views = views;

        setupTouchesListener();
    }

    private void setupTouchesListener(){
        views[0].setOnTouchListener(new TouchesHandler(this));
    }

    public void setScrambleShown(String scramble){
        TextView tv = getScrambleView();
        tv.setText(scramble);
    }

    String getScrambleShown(){
        TextView tv = getScrambleView();
        return tv.getText().toString();
    }

    public RelativeLayout getMainScreen(){
        return (RelativeLayout) views[0];
    }

    TextView getDisplay(){
        return (TextView) views[1];
    }

    TextView getScrambleView(){
        return (TextView) views[2];
    }

    CheckBox getToggleInsp(){
        return (CheckBox) views[3];
    }

    TextView getCurrPhaseView(){
        return (TextView) views[4];
    }

    RadioGroup getRadioGroup(){
        return (RadioGroup) views[5];
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