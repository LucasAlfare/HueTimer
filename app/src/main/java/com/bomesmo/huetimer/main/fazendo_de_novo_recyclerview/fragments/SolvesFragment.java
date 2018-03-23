package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.auxiliar.TF;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.auxiliar.AnimatedExpandableListView;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.auxiliar.SolvesListAdapter;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.Solve;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud.Create;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud.Delete;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud.Read;

import java.util.ArrayList;

/**
 * Created by Lucas on 03/03/2018.
 */

public class SolvesFragment extends Fragment {

    public static AnimatedExpandableListView animatedListView;//estático para poder acessar everywhere
    public static Activity host;
    private SolvesListAdapter solvesListAdapter;
    private Button clear;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        host = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View holder = inflater.inflate(R.layout.fragment_times_list, container, false);

        animatedListView = holder.findViewById(R.id.animatedListView);
        clear = holder.findViewById(R.id.clear);

        animatedListView.setGroupIndicator(getResources().getDrawable(R.drawable.exapandable_listview_indicator));
        solvesListAdapter = new SolvesListAdapter(getContext());
        animatedListView.setAdapter(solvesListAdapter);
        animatedListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                ArrayList<Solve> solves = new Read().getSolves();
                final Solve selected = solves.get(position);
                alert.setTitle("Delete the solve " + TF.longToTimestamp(selected.getTotalSolveTime()) + "???");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (new Delete().removerSolve(selected)) {
                            Toast.makeText(getContext(), "Solve deleted.", Toast.LENGTH_SHORT).show();
                            animatedListView.setAdapter(new SolvesListAdapter(getContext()));
                        } else {
                            Toast.makeText(getContext(), "Something wrong happened :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Nooo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //pass
                    }
                });

                alert.show();

                return false;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a = new AlertDialog.Builder(getContext());
                a.setTitle("DELETE ALL????");

                a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Delete().removerTabela();
                        new Create().criarTabela();
                        animatedListView.setAdapter(new SolvesListAdapter(getContext()));

                        Toast.makeText(getContext(), "DELETED!!!", Toast.LENGTH_SHORT).show();
                    }
                });

                a.show();
            }
        });

        return holder;
    }
}
