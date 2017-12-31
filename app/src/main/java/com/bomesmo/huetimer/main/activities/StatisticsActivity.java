package com.bomesmo.huetimer.main.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
     * o nome da esta tística + o resutlado da mesma. Além disso, cada botão terá um listener definido.
     * @param stats Array de estatísticas utilizado para construir os botoões.
     */
    private void setupStatsButtons(final Statistic[] stats){
        for (Statistic stat : stats) {
            Button curr = new Button(getApplicationContext());
            curr.setText(stat.name() + ": " + (stat.result() != 0 ? TF.format(stat.result()) : "- -"));

            if (stat.result() != 0){
                curr.setOnClickListener(new StatButtonListener(stat.name(), stat.result(), stat.details()));
            } else {
                curr.setEnabled(false);
            }

            statisticsScreen.addView(curr);
        }
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
            final AlertDialog.Builder alert = new AlertDialog.Builder(StatisticsActivity.this);
            alert.setTitle(statisticName);

            /*
            Contruindo layout simples para ser exibido no alert
             */
            LinearLayout ll = new LinearLayout(getApplicationContext());
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            ll.setOrientation(LinearLayout.VERTICAL);

            TextView tv = new TextView(getApplicationContext());
            tv.setTextColor(Color.DKGRAY);
            tv.setText("solves:");

            /*
            EditText que mostra várias linhas e que não permite edições, apenas seleção de texto
             */
            EditText details = new EditText(getApplicationContext());
            details.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
            details.setTextIsSelectable(true);
            details.setSingleLine(false);
            details.setKeyListener(null);
            details.setTextSize(14f);
            details.setHint("stat...");
            details.setTextColor(Color.BLACK);

            if (!statisticDetails.equals("")) details.setText(statisticDetails);

            ll.addView(tv);
            ll.addView(details);

            alert.setView(ll);

            /*
            botão positivo do alert implementa compartilhamento do conteúdo do EdtText
             */
            alert.setPositiveButton("Compartilhar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);

                            String value = "TEXTO GERADO PELO HUETIMER!! afff kkkk" + "\n" +
                            "Resultado da estatística: " + TF.format(statisticResult) + "\n" +
                            "Solves da estatística: " + statisticDetails;

                    shareIntent.putExtra(Intent.EXTRA_TEXT, value);
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
