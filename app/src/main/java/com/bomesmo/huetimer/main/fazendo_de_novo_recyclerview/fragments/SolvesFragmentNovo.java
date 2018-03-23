package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bomesmo.huetimer.main.R;
import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.recycler.SwipeAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

/**
 * Created by Lucas on 23/03/2018.
 */

public class SolvesFragmentNovo extends Fragment {

    public static RecyclerView recyclerView;
    private Button clear2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View xml = LayoutInflater.from(getActivity()).inflate(R.layout.swipe_solves_fragment, container, false);
        recyclerView = xml.findViewById(R.id.recycler_swipe);
        clear2 = xml.findViewById(R.id.clear2);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewSwipeManager manager = new RecyclerViewSwipeManager();
        recyclerView.setAdapter(manager.createWrappedAdapter(new SwipeAdapter(getActivity())));
        manager.attachRecyclerView(recyclerView);

        return xml;
    }
}
