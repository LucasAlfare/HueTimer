package com.bomesmo.huetimer.main.fazendo_de_novo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.GlobalConfiguration;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud.CreateConfig;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud.ReadConfig;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud.UpdateConfig;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Create;
import com.bomesmo.huetimer.main.fazendo_de_novo.fragments.SolvesFragment;
import com.bomesmo.huetimer.main.fazendo_de_novo.fragments.StatisticsFragment;
import com.bomesmo.huetimer.main.fazendo_de_novo.fragments.TimerFragment;

import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {

    public int numPhases = 1;
    public int holdingTime = 1;
    private GlobalConfiguration globalConfiguration;

    private FloatingActionButton quick;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        quick = findViewById(R.id.fabPreferences);
        quick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                alert.setTitle("Configurações rápidas");

                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.settings_fab2_layout, null);

                alert.setView(view);

                final SeekBar seekBar = view.findViewById(R.id.minhaBarra);
                seekBar.setProgress(holdingTime / 10);

                final TextView seekBarTarget = view.findViewById(R.id.seekBarTarget);
                seekBarTarget.setText((holdingTime) + " milissegundos");

                final CheckBox useSounds = view.findViewById(R.id.useSounds);

                final Button decreasePhases = view.findViewById(R.id.decreasePhases);
                final TextView currNumberOfPhases = view.findViewById(R.id.currNumberOfPhases);
                currNumberOfPhases.setText(String.valueOf(numPhases));
                final Button increasePhases = view.findViewById(R.id.increasePhases);

                decreasePhases.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (numPhases > 1) {
                            numPhases--;
                        }
                        currNumberOfPhases.setText(String.valueOf(numPhases));
                    }
                });

                increasePhases.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        numPhases++;
                        currNumberOfPhases.setText(String.valueOf(numPhases));
                    }
                });

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        holdingTime = progress * 10;
                        seekBarTarget.setText((holdingTime) + " milissegundos");
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
                        globalConfiguration.setHoldTime(holdingTime);
                        globalConfiguration.setNumPhases(numPhases);
                        globalConfiguration.setUseInspection(false);
                        if (new UpdateConfig().updateConfig(globalConfiguration)) {
                            Toast.makeText(MainActivity2.this, "The preferences has been saved.", Toast.LENGTH_SHORT).show();
                        }
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

        fab = findViewById(R.id.fabQuick);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "quick averages goes here", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity2.this, "botao da snackbar", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    private void init() {
        new Create().criarTabela();
        new CreateConfig().criarTabela();

        //loads user configurations saved or create one first time
        if (new ReadConfig().getGlobalConfiguration() != null) {
            globalConfiguration = new ReadConfig().getGlobalConfiguration();
            numPhases = globalConfiguration.getNumPhases();
            holdingTime = globalConfiguration.getHoldTime();
            Toast.makeText(this, "Your configurations has been loaded.", Toast.LENGTH_SHORT).show();
        } else {
            globalConfiguration = new GlobalConfiguration(UUID.randomUUID(), 300, 1, false);
            if (new UpdateConfig().addConfig(globalConfiguration)) {
                Toast.makeText(this, "Standard configurations has been created. :)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new TimerFragment();
                case 1:
                    return new SolvesFragment();
                case 2:
                    return new StatisticsFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
