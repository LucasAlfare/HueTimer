package com.bomesmo.huetimer.main.auxiliar;

/**
 * Created by Lucas on 24/12/2017.
 *
 * Esta classe é utilizada para manter os parâmetros de tempo e embaralhamento.
 */

public class Solve {

    private long time;
    private String scramble;

    public Solve(long time, String scramble) {
        this.time = time;
        this.scramble = scramble;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    @Override
    public String toString() {
        return TF.longToTimestamp(time) + "\t" + scramble;
    }
}
