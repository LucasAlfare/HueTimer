package com.bomesmo.huetimer.main.statistics;

import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.statistics.auxiliar.Misc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lucas on 30/12/2017.
 */

public class Worst extends Statistic {

    public Worst(ArrayList<Solve> solves) {
        super(solves);
    }

    @Override
    public String name() {
        return "Pior tempo";
    }

    @Override
    public long result() {
        ArrayList<Long> times = Misc.getTimes(solves);

        return !solves.isEmpty() ? Collections.max(times) : 0;
    }

    @Override
    public String details() {
        return !solves.isEmpty() ? solves.get(Misc.indexOfWorst(solves)).toString() : "";
    }
}
