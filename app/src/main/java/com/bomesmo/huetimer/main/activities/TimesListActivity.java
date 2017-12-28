package com.bomesmo.huetimer.main.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.AnimatedExpandableListView;
import com.bomesmo.huetimer.main.auxiliar.AnimatedExpandableListViewAdapter;
import com.bomesmo.huetimer.main.auxiliar.PreferencesHelper;
import com.bomesmo.huetimer.main.auxiliar.SolvesHandler;
import com.bomesmo.huetimer.main.core.Solve;

import java.util.ArrayList;

public class TimesListActivity extends AppCompatActivity {

    private ArrayList<Solve> solves;
    private AnimatedExpandableListView listaAnimada;
    private Button button;

    private final SharedPreferences.OnSharedPreferenceChangeListener PREFERENCES_CHANGE_LISTENER = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("solves")){
                ArrayList<Solve> aux = PreferencesHelper.dataContains(getApplicationContext(), key)
                        ?
                        SolvesHandler.getSolves(getApplicationContext())
                        :
                        new ArrayList<Solve>();

                listaAnimada.setAdapter(new AnimatedExpandableListViewAdapter(getApplicationContext(), TimesListActivity.this, aux));
                Toast.makeText(TimesListActivity.this, "atualizado..", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_list);
        init();

        if (!PreferencesHelper.dataContains(getApplicationContext(), "solves")){
            solves = new ArrayList<>();
        } else {
            solves = PreferencesHelper.getSolvesList(getApplicationContext(), "solves");
            if (solves.size() > 0){
                Snackbar.make(button, "Solves foram carregadas", Snackbar.LENGTH_SHORT).show();
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesHelper.clearAllSolvesData(getApplicationContext());
                solves.clear();
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.registerOnSharedPreferenceChangeListener(PREFERENCES_CHANGE_LISTENER);

        listaAnimada.setGroupIndicator(getResources().getDrawable(R.drawable.exapandable_listview_indicator));
        listaAnimada.setAdapter(new AnimatedExpandableListViewAdapter(getApplicationContext(), this, solves));
    }

    private void init() {
        listaAnimada = findViewById(R.id.animatedListView);
        button = findViewById(R.id.clear);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.unregisterOnSharedPreferenceChangeListener(PREFERENCES_CHANGE_LISTENER);
    }
}
