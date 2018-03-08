package com.bomesmo.huetimer.main.fazendo_de_novo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo.MainActivity2;
import com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar.AdapterNovo;
import com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar.TF;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.Core;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.Solve;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Read;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Update;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Lucas on 03/03/2018.
 */
public class TimerFragment extends Fragment {

    private RelativeLayout mainScreen;
    private TextView display, scrambleView, currPhaseView;
    private CheckBox toggleInspection;
    private RadioGroup radioGroup;

    private Core core;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View holder = inflater.inflate(R.layout.fragment_timer, container, false);

        mainScreen = holder.findViewById(R.id.telaPrincipal);
        display = holder.findViewById(R.id.display);
        scrambleView = holder.findViewById(R.id.scramble);
        toggleInspection = holder.findViewById(R.id.setInsp);
        currPhaseView = holder.findViewById(R.id.currPhaseView);
        radioGroup = holder.findViewById(R.id.radioGroup);

        final ArrayList<Solve> solves = new Read().getSolves();

        if (solves.size() > 0){
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    View radioButton = radioGroup.findViewById(checkedId);
                    int index = radioGroup.indexOfChild(radioButton);

                    Solve current = solves.get(solves.size() - 1);
                    switch (index){
                        case 0:
                            new Update().updateSolve(
                                    new Solve(
                                            UUID.fromString(current.getUuid()),
                                            current.getPhasesTimes(),
                                            current.getScramble(),
                                            false,
                                            false));
                            display.setText(
                                    TF.longToTimestamp(
                                            solves.get(solves.size() - 1).getPhasesTimes().get(MainActivity2.numPhases - 1)));
                            SolvesFragment.animatedListView.setAdapter(new AdapterNovo(getContext()));
                            break;
                        case 1:
                            new Update().updateSolve(
                                    new Solve(
                                            UUID.fromString(current.getUuid()),
                                            current.getPhasesTimes(),
                                            current.getScramble(),
                                            false,
                                            true));
                            display.setText("+" + TF.longToTimestamp(solves.get(solves.size() - 1).getPhasesTimes().get(MainActivity2.numPhases - 1) + 2000));
                            SolvesFragment.animatedListView.setAdapter(new AdapterNovo(getContext()));
                            break;
                        case 2:
                            new Update().updateSolve(
                                    new Solve(
                                            UUID.fromString(current.getUuid()),
                                            current.getPhasesTimes(),
                                            current.getScramble(),
                                            true,
                                            false));
                            display.setText("DNF");
                            SolvesFragment.animatedListView.setAdapter(new AdapterNovo(getContext()));
                            break;
                    }
                }
            });
        }

        core = new Core(
                mainScreen,
                display,
                scrambleView,
                toggleInspection,
                currPhaseView,
                radioGroup);

        return holder;
    }
}
