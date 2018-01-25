package com.bomesmo.huetimer.main.activities.fewst_moves_helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bomesmo.huetimer.main.R;

import java.util.ArrayList;

public class FewestMovesHelperActivity extends AppCompatActivity {

    private EditText scrambleSequenceBox, solveSequenceBox;
    private ArrayList<String> moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fewst_moves_helper);

        init();
    }

    private void init(){
        scrambleSequenceBox = findViewById(R.id.scrambleSequenceBox);
        solveSequenceBox = findViewById(R.id.solveSequenceBox);
        moves = new ArrayList<>();
    }

    public void updateSolveSequenceBox(View bt){
        moves.add(bt.getTag().toString());

        String currTxt = solveSequenceBox.getText().toString();
        solveSequenceBox.setText(currTxt + bt.getTag().toString() + " ");
    }

    public void backspace(View bt){
        moves.remove(moves.size() - 1);

        StringBuilder aux = new StringBuilder();

        for (String x : moves){
            aux.append(x).append(" ");
        }

        solveSequenceBox.setText(aux.toString());
    }

    public void verScramble(View bt){
        setupAlert("Seu scramble", scrambleSequenceBox.getText().toString().trim());
    }

    public void verSolve(View bt){
        setupAlert("Sua solve", scrambleSequenceBox.getText().toString().trim() + " " + solveSequenceBox.getText().toString().trim());
    }

    private void setupAlert(String tittle, String sequence){
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.aux_cubeview, null, false);

        CubeView cubeView = view.findViewById(R.id.cubeView);

        cubeView.getCube().applySequence(sequence);


        final AlertDialog.Builder alert = new AlertDialog.Builder(FewestMovesHelperActivity.this);
        alert.setTitle(tittle);

        alert.setView(view);

        alert.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //pass
            }
        });

        alert.show();
    }
}
