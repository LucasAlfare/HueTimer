package com.bomesmo.huetimer.main.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.Adaptador;
import com.bomesmo.huetimer.main.core.Solve;

import java.util.ArrayList;

public class TimesListActivity extends AppCompatActivity {

    private ArrayList<Solve> solves;
    private ExpandableListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_list);
        init();

        Adaptador adaptador = new Adaptador(getApplicationContext(), solves);
        lista.setAdapter(adaptador);
    }

    private void init() {
        solves = new ArrayList<>();
        lista = findViewById(R.id.listView);
    }
}
