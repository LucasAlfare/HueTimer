package com.bomesmo.huetimer.main.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.activities.fewst_moves_helper.FewestMovesHelperActivity;
import com.bomesmo.huetimer.main.activities.square1_csp_trainer.CspTrainerActivity;
import com.bomesmo.huetimer.main.auxiliar.PreferencesHelper;
import com.bomesmo.huetimer.main.auxiliar.SolvesHandler;
import com.bomesmo.huetimer.main.auxiliar.TF;
import com.bomesmo.huetimer.main.core.Core;
import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.puzzles.scrambles.Scramble;
import com.bomesmo.huetimer.main.statistics.CurrentAvgX;
import com.bomesmo.huetimer.main.statistics.Statistic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout mainScreen;
    private TextView display, scrambleView;
    private FloatingActionButton fab, fab2;
    private CheckBox toggleInspection;
    private ArrayList<Solve> solves;
    private Core core;

    private int pressing_time;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        startActivity(new Intent(getApplicationContext(), CspTrainerActivity.class));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Statistic avg5 = new CurrentAvgX(SolvesHandler.getSolves(getApplicationContext()), 5);
                Statistic avg12 = new CurrentAvgX(SolvesHandler.getSolves(getApplicationContext()), 12);

                String label =
                        "Avg5: " + (avg5.result() != 0 ? TF.longToTimestamp(avg5.result()) : "- -")  + "\n" +
                        "Avg12: " + (avg12.result() != 0 ? TF.longToTimestamp(avg12.result()) : "- -");

                Snackbar.make(view, label, Snackbar.LENGTH_LONG)
                        .setAction("Deletar último", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<Solve> salvos;

                                if (PreferencesHelper.dataContains(getApplicationContext(), "solves")) {
                                    salvos = SolvesHandler.getSolves(getApplicationContext());
                                    SolvesHandler.removeSolve(getApplicationContext(), salvos.size() - 1);
                                    Snackbar snackbar1 = Snackbar.make(v, "Deletado!", Snackbar.LENGTH_LONG);
                                    snackbar1.show();
                                    display.setText("pronto!");
                                }
                            }
                        }).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Configurações rápidas");

                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.settings_fab2_layout, null);

                alert.setView(view);

                final SeekBar seekBar = view.findViewById(R.id.minhaBarra);
                seekBar.setProgress(pressing_time / 10);

                final TextView seekBarTarget = view.findViewById(R.id.seekBarTarget);
                seekBarTarget.setText((pressing_time) + " milissegundos");

                final CheckBox useSounds = view.findViewById(R.id.useSounds);

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        pressing_time = progress * 10;
                        seekBarTarget.setText((pressing_time) + " milissegundos");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                alert.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferencesHelper.addIntegerPreference(getApplicationContext(), "pressing_time", pressing_time);
                        Toast.makeText(MainActivity.this, "Todas as preferências foram salvas!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //pass
                    }
                });

                alert.show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init(){
        mainScreen = findViewById(R.id.mainScreen);
        display = findViewById(R.id.display);
        scrambleView = findViewById(R.id.scramble);
        fab = findViewById(R.id.fabQuick);
        fab2 = findViewById(R.id.fabPreferences);
        toggleInspection = findViewById(R.id.setInsp);

        solves = SolvesHandler.getSolves(getApplicationContext());

        core = new Core(MainActivity.this, mainScreen, display, scrambleView, fab, toggleInspection, fab2);
        core.setScrambleID(Scramble.RUBIKS_ID);

        String scrambleSequence = Scramble.getScrambleByID(core.getScrambleID());
        core.setScrambleShown(scrambleSequence);

        if (solves.isEmpty()) display.setText("pronto!");

        if (PreferencesHelper.dataContains(getApplicationContext(), "pressing_time")){
            pressing_time = PreferencesHelper.getIntegerPreference(getApplicationContext(), "pressing_time");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (solves.isEmpty()){
            display.setText("pronto!");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.setRubiks) {
            core.setScrambleID(Scramble.RUBIKS_ID);
            String scrambleSequence = Scramble.getScrambleByID(Scramble.RUBIKS_ID);
            core.setScrambleShown(scrambleSequence);
            return true;
        } else if (id == R.id.setPocket){
            core.setScrambleID(Scramble.POCKET_ID);
            String scrambleSequence = Scramble.getScrambleByID(Scramble.POCKET_ID);
            core.setScrambleShown(scrambleSequence);
            return true;
        } else if (id == R.id.setSkewb){
            core.setScrambleID(Scramble.SKEWB_ID);
            String scrambleSequence = Scramble.getScrambleByID(Scramble.SKEWB_ID);
            core.setScrambleShown(scrambleSequence);
            return true;
        } else if (id == R.id.setClock){
            core.setScrambleID(Scramble.CLOCK_ID);
            String scrambleSequence = Scramble.getScrambleByID(Scramble.CLOCK_ID);
            core.setScrambleShown(scrambleSequence);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_solves_list) {
            intent = new Intent(this, TimesListActivity.class);
        } else if (id == R.id.nav_statistics) {
            intent = new Intent(this, StatisticsActivity.class);
        } else if (id == R.id.about) {
            intent = new Intent(this, AboutActivity.class);
        } else if (id == R.id.nav_fm_helper){
            intent = new Intent(this, FewestMovesHelperActivity.class);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (intent != null) startActivity(intent);
        return true;
    }
}


