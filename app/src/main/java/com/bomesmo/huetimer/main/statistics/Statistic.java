package com.bomesmo.huetimer.main.statistics;

import com.bomesmo.huetimer.main.auxiliar.Solve;

import java.util.ArrayList;

/**
 * Created by Lucas on 30/12/2017.
 */

public abstract class Statistic {

    public ArrayList<Solve> solves;

    public Statistic(ArrayList<Solve> solves) {
        this.solves = solves;
    }

    public abstract String name();
    public abstract long result();
    public abstract String details();
}
