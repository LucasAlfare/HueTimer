package com.bomesmo.huetimer.main.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.PreferencesHelper;
import com.bomesmo.huetimer.main.auxiliar.SolvesHandler;
import com.bomesmo.huetimer.main.auxiliar.TF;
import com.bomesmo.huetimer.main.core.Core;
import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.scrambles.Scramble;
import com.bomesmo.huetimer.main.statistics.CurrentAvgX;
import com.bomesmo.huetimer.main.statistics.Statistic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout mainScreen;
    private TextView display, scrambleView;
    private FloatingActionButton fab;
    private CheckBox setInspection;
    private ArrayList<Solve> solves;
    private Core core;
    private Scramble scrambleSequence;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainScreen = findViewById(R.id.mainScreen);
        display = findViewById(R.id.display);
        scrambleView = findViewById(R.id.scramble);
        fab = findViewById(R.id.fab);
        setInspection = findViewById(R.id.setInsp);

        solves = SolvesHandler.getSolves(getApplicationContext());
        core = new Core(MainActivity.this, mainScreen, display, scrambleView, fab, setInspection);
        core.setScrambleID(Scramble.RUBIKS_ID);

        String scrambleSequence = Scramble.getScrambleByID(core.getScrambleID());
        core.setScrambleShown(scrambleSequence);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (solves.isEmpty()){
            display.setText("pronto!");
        }

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressLint("RestrictedApi")
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_solves_list) {
            intent = new Intent(this, TimesListActivity.class);
        } else if (id == R.id.nav_statistics) {
            intent = new Intent(this, StatisticsActivity.class);
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (intent != null) startActivity(intent);
        return true;
    }
}