package com.bomesmo.huetimer.main.statistics;

import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.statistics.auxiliar.Misc;

import java.util.ArrayList;

/**
 * Created by Lucas on 30/12/2017.
 */

public class OverrMean extends Statistic {

    public OverrMean(ArrayList<Solve> solves) {
        super(solves);
    }

    @Override
    public String name() {
        return "Média geral";
    }

    @Override
    public long result() {
        long sum = 0;

        for (Solve x : solves){
            sum += x.getTime();
        }

        return solves.size() > 0 ? sum / solves.size() : 0;
    }

    @Override
    public String details() {
        return solves.size() >= 2 ? Misc.statisticDetails(solves.toArray(new Solve[solves.size()])) : "";
    }
}
