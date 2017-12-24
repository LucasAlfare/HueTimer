package com.bomesmo.huetimer.main.core;

import com.bomesmo.huetimer.main.auxiliar.TF;

/**
 * Created by Lucas on 24/12/2017.
 */

public class Solve {

    private long time;
    private String scramble;

    public Solve(long time, String scramble) {
        this.time = time;
        this.scramble = scramble;
    }

    @Override
    public String toString() {
        return TF.format(time) + "\t" + scramble;
    }
}
