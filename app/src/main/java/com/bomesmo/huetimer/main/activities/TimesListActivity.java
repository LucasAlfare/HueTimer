package com.bomesmo.huetimer.main.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.AnimatedExpandableListView;
import com.bomesmo.huetimer.main.auxiliar.AnimatedExpandableListViewAdapter;
import com.bomesmo.huetimer.main.auxiliar.PreferencesHelper;
import com.bomesmo.huetimer.main.core.Solve;

import java.util.ArrayList;

public class TimesListActivity extends AppCompatActivity {

    private ArrayList<Solve> solves;
    private AnimatedExpandableListView listaAnimada;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_list);
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesHelper.clearAllData(getApplicationContext());
                solves.clear();
                listaAnimada.setAdapter(new AnimatedExpandableListViewAdapter(getApplicationContext(), solves));
            }
        });

        listaAnimada.setGroupIndicator(getResources().getDrawable(R.drawable.exapandable_listview_indicator));
        listaAnimada.setAdapter(new AnimatedExpandableListViewAdapter(getApplicationContext(), solves));
    }

    private void init() {
        listaAnimada = findViewById(R.id.animatedListView);
        button = findViewById(R.id.clear);
        if (!PreferencesHelper.dataContains(getApplicationContext(), "solves")){
            solves = new ArrayList<>();
        } else {
            solves = PreferencesHelper.getSolvesList(getApplicationContext(), "solves");
        }
    }
}
