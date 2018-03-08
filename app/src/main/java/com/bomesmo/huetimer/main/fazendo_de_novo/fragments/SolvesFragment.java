package com.bomesmo.huetimer.main.fazendo_de_novo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar.AdapterNovo;
import com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar.AnimatedExpandableListView;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Create;
import com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud.Delete;

/**
 * Created by Lucas on 03/03/2018.
 */

public class SolvesFragment extends Fragment {

    public static AnimatedExpandableListView animatedListView;//estático para poder acessar everywhere
    private AdapterNovo adapterNovo;
    private Button clear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View holder = inflater.inflate(R.layout.activity_times_list, container, false);

        animatedListView = holder.findViewById(R.id.animatedListView);
        clear = holder.findViewById(R.id.clear);

        animatedListView.setGroupIndicator(getResources().getDrawable(R.drawable.exapandable_listview_indicator));
        adapterNovo = new AdapterNovo(getContext());
        animatedListView.setAdapter(adapterNovo);
        
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Delete().removerTabela();
                new Create().criarTabela();
                animatedListView.setAdapter(new AdapterNovo(getContext()));
            }
        });
        return holder;
    }
}
