package com.bomesmo.huetimer.main.statistics;

import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.statistics.auxiliar.Misc;

import java.util.ArrayList;

/**
 * Created by Lucas on 30/12/2017.
 */

public class WorstAvgX extends Statistic {

    private int averageLength;

    public WorstAvgX(ArrayList<Solve> solves, int averageLength) {
        super(solves);
        this.averageLength = averageLength;
    }

    @Override
    public String name() {
        return "Pior média de " + averageLength;
    }

    @Override
    public long result() {
        return solves.size() < averageLength ? 0 : Misc.averageFrom(Misc.averageSolves(solves, averageLength, false));
    }

    @Override
    public String details() {
        String r = "";

        if (solves.size() >= averageLength){
            Solve[] result = Misc.averageSolves(solves, averageLength, false);

            r = Misc.statisticDetails(result);
        }

        return r;
    }

    public int getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(int averageLength) {
        this.averageLength = averageLength;
    }
}
