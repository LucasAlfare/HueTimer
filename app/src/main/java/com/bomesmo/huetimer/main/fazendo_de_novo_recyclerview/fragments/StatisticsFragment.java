package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bomesmo.huetimer.main.R;

/**
 * Created by Lucas on 03/03/2018.
 */

public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_statistics, container, false);
    }
}
