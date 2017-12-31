package com.bomesmo.huetimer.main.statistics;

import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.statistics.Statistic;
import com.bomesmo.huetimer.main.statistics.auxiliar.Misc;

import java.util.ArrayList;

/**
 * Created by Lucas on 30/12/2017.
 */

public class OverrAverage extends Statistic {

    public OverrAverage(ArrayList<Solve> solves) {
        super(solves);
    }

    @Override
    public String name() {
        return "Média Truncada geral";
    }

    @Override
    public long result() {
        return solves.size() >= 3 ? Misc.averageFrom(solves) : 0;
    }

    @Override
    public String details() {
        String r = "";

        if (solves.size() >= 3){
            ArrayList<Solve> result = new ArrayList<>();

            for (int i = 0; i < solves.size(); i++){
                if (!Misc.isBest(solves.get(i), solves.toArray(new Solve[solves.size()])) ||
                        Misc.isWorst(solves.get(i), solves.toArray(new Solve[solves.size()]))){

                    result.add(solves.get(i));
                }
            }

            r = Misc.statisticDetails(result.toArray(new Solve[result.size()]));
        }

        return r;
    }
}
