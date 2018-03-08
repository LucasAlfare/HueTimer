package com.bomesmo.huetimer.main.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.PreferencesHelper;
import com.bomesmo.huetimer.main.auxiliar.SolvesHandler;
import com.bomesmo.huetimer.main.auxiliar.Solve;

import java.util.ArrayList;

public class TimesListActivity extends AppCompatActivity {

//    private ArrayList<Solve> solves;
//    private AnimatedExpandableListView listaAnimada;
//    private Button button;
//
//    private final OnSharedPreferenceChangeListener PREFERENCES_CHANGE_LISTENER = new OnSharedPreferenceChangeListener() {
//        @Override
//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            if (key.equals("solves")){
//                ArrayList<Solve> aux = PreferencesHelper.dataContains(getApplicationContext(), key)
//                        ?
//                        SolvesHandler.getSolves(getApplicationContext())
//                        :
//                        new ArrayList<Solve>();
//
//                listaAnimada.setAdapter(new AnimatedExpandableListViewAdapter(getApplicationContext(), TimesListActivity.this, aux));
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_times_list);
//        init();
//
//        solves = SolvesHandler.getSolves(getApplicationContext());
//
//        if (solves.size() > 0){
//            Snackbar.make(button, "Solves foram carregadas", Snackbar.LENGTH_SHORT).show();
//        }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final AlertDialog.Builder alert = new AlertDialog.Builder(TimesListActivity.this);
//
//                alert.setTitle("ATENÇÃO!!");
//                alert.setMessage("Excluir TODOS OS TEMPOS da memória???");
//
//                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        PreferencesHelper.clearAllSolvesData(getApplicationContext());
//                        solves.clear();
//                        Toast.makeText(getApplicationContext(), "todos os tempos foram apagados!!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //pass
//                    }
//                });
//
//                alert.show();
//            }
//        });
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        prefs.registerOnSharedPreferenceChangeListener(PREFERENCES_CHANGE_LISTENER);
//
//        listaAnimada.setGroupIndicator(getResources().getDrawable(R.drawable.exapandable_listview_indicator));
//        listaAnimada.setAdapter(new AnimatedExpandableListViewAdapter(getApplicationContext(), this, solves));
//    }
//
//    private void init() {
//        listaAnimada = findViewById(R.id.animatedListView);
//        button = findViewById(R.id.clear);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        prefs.unregisterOnSharedPreferenceChangeListener(PREFERENCES_CHANGE_LISTENER);
//    }
}
