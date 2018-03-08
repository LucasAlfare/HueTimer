package com.bomesmo.huetimer.main.fazendo_de_novo.core;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Lucas on 01/03/2018.
 */

public class Solve {

    private UUID uuid;
    private ArrayList<Long> time;
    private String scramble;
    private boolean isDNF;
    private boolean isPlus2;

    public Solve(UUID uuid) {
        this.uuid = uuid;
    }

    public Solve(UUID uuid, ArrayList<Long> time, String scramble, boolean isDNF, boolean isPlus2) {
        this.uuid = uuid;
        this.time = time;
        this.scramble = scramble;
        this.isDNF = isDNF;
        this.isPlus2 = isPlus2;
    }

    //TO DATABASE...
    public String phasesTimesToJsonString(){
        return new Gson().toJson(time);
    }

    public long getTotalSolveTime(){
        long sum = 0;
        for (long x : getPhasesTimes()){
            sum += x;
        }

        return sum;
    }

    public String getUuid() {
        return uuid.toString();
    }

    public ArrayList<Long> getTime() {
        return time;
    }

    public ArrayList<Long> getPhasesTimes() {
        return time;
    }

    public void setTime(ArrayList<Long> time) {
        this.time = time;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public boolean isDNF() {
        return isDNF;
    }

    public void setDNF(boolean DNF) {
        isDNF = DNF;
    }

    public boolean isPlus2() {
        return isPlus2;
    }

    public void setPlus2(boolean plus2) {
        isPlus2 = plus2;
    }

    @Override
    public String toString() {
        //return (isDNF() ? ("DNF") : (isPlus2() ? (time + 2000) + "+" : time)) + "\t" + scramble;
        return super.toString();
    }
}