package com.bomesmo.huetimer.main.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.auxiliar.SolvesHandler;
import com.bomesmo.huetimer.main.auxiliar.TF;
import com.bomesmo.huetimer.main.statistics.Best;
import com.bomesmo.huetimer.main.statistics.BestAvgX;
import com.bomesmo.huetimer.main.statistics.CurrentAvgX;
import com.bomesmo.huetimer.main.statistics.OverrAverage;
import com.bomesmo.huetimer.main.statistics.OverrMean;
import com.bomesmo.huetimer.main.statistics.Statistic;
import com.bomesmo.huetimer.main.statistics.Worst;
import com.bomesmo.huetimer.main.statistics.WorstAvgX;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    private LinearLayout statisticsScreen;
    private ArrayList<Solve> solves;
    private Statistic[] statistics;
    private Button toList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        init();

        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, TimesListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        statisticsScreen = findViewById(R.id.statisticsScreen);
        toList = findViewById(R.id.toList);
        solves = SolvesHandler.getSolves(getApplicationContext());

        statistics = new Statistic[]{
                new Best(solves),
                new Worst(solves),
                new OverrMean(solves),
                new OverrAverage(solves),

                new CurrentAvgX(solves, 5),
                new BestAvgX(solves, 5),
                new WorstAvgX(solves, 5),

                new CurrentAvgX(solves, 12),
                new BestAvgX(solves, 12),
                new WorstAvgX(solves, 12),

                new CurrentAvgX(solves, 50),
                new BestAvgX(solves, 50),
                new WorstAvgX(solves, 50),

                new CurrentAvgX(solves, 100),
                new BestAvgX(solves, 100),
                new WorstAvgX(solves, 100)
        };

        setupStatsButtons(statistics);
    }

    /**
     * Constrói um botão para cada estatística repassada. Cada botão terá o seu texto setado para
     * o nome da esta tística + resutlado da mesma. Além disso, cada botão terá um listener definido.
     * @param stats Array de estatísticas utilizado para construir os botoões.
     */
    private void setupStatsButtons(final Statistic[] stats){
        statisticsScreen.removeAllViewsInLayout();
        for (Statistic stat : stats) {
            Button curr = new Button(getApplicationContext());
            curr.setText(stat.name() + ": " + (stat.result() != 0 ? TF.longToTimestamp(stat.result()) : "- -"));

            if (stat.result() != 0){
                curr.setOnClickListener(new StatButtonListener(stat.name(), stat.result(), stat.details()));
            } else {
                curr.setEnabled(false);
            }

            statisticsScreen.addView(curr);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupStatsButtons(statistics);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setupStatsButtons(statistics);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupStatsButtons(statistics);
    }

    /**
     * Classe para armazenar o listener dos botões criados programaticamente.
     */
    private class StatButtonListener implements View.OnClickListener {

        private String statisticName;
        private long statisticResult;
        private String statisticDetails;

        StatButtonListener(String statisticName, long statisticResult, String statisticDetails) {
            this.statisticName = statisticName;
            this.statisticResult = statisticResult;
            this.statisticDetails = statisticDetails;
        }

        @Override
        public void onClick(View v) {
            /*
            EditText que mostra várias linhas e que não permite edições, apenas seleção de texto
             */

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            final View view = layoutInflater.inflate(R.layout.alert_statistic_detail, null, false);

            final AlertDialog.Builder alert = new AlertDialog.Builder(StatisticsActivity.this);
            alert.setTitle(statisticName);

            final EditText details = view.findViewById(R.id.detailsBox);
            if (!statisticDetails.equals("")) details.setText(statisticDetails);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    details.selectAll();
                }
            });

            alert.setView(view);

            /*
            botão positivo do alert implementa compartilhamento do conteúdo do EdtText
             */
            alert.setPositiveButton("Compartilhar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);

                            String value = "TEXTO GERADO PELO HUETIMER!! afff kkkk" + "\n\n\n" +
                            "Resultado/" + statisticName + ": " + TF.longToTimestamp(statisticResult) + "\n\n" +
                            "Solves da estatística: " + statisticDetails;

                    shareIntent.putExtra(Intent.EXTRA_TEXT, value.trim());
                    shareIntent.setType("text/plain");

                    startActivity(shareIntent);
                }
            });

            alert.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //pass
                }
            });

            alert.show();
        }
    }
}
