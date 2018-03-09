package com.bomesmo.huetimer.main.fazendo_de_novo.core;

import java.util.UUID;

/**
 * Created by Lucas on 08/03/2018.
 *
 * VOU USAR ISSO PRA GUARDAR TODAS AS CONFIGS DO APP EM UMA OUTRA TABLE
 */
public class GlobalConfiguration {

    private UUID uuid;
    private int holdTime;
    private int numPhases;
    private boolean useInspection;

    public GlobalConfiguration() {
        //pass
    }

    public GlobalConfiguration(UUID uuid) {
        this.uuid = uuid;
    }

    public GlobalConfiguration(UUID uuid, int holdTime, int numPhases, boolean useInspection) {
        this.uuid = uuid;
        this.holdTime = holdTime;
        this.numPhases = numPhases;
        this.useInspection = useInspection;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(int holdTime) {
        this.holdTime = holdTime;
    }

    public int getNumPhases() {
        return numPhases;
    }

    public void setNumPhases(int numPhases) {
        this.numPhases = numPhases;
    }

    public boolean isUseInspection() {
        return useInspection;
    }

    public void setUseInspection(boolean useInspection) {
        this.useInspection = useInspection;
    }
}
