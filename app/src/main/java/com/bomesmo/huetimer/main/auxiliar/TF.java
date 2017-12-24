package com.bomesmo.huetimer.main.auxiliar;


import java.text.SimpleDateFormat;

/**
 * Created by Lucas on 24/12/2017.
 */

public class TF {

    public static String format(long time){
        long minutes = Long.parseLong(new SimpleDateFormat("mm").format(time));
        return minutes > 0 ? new SimpleDateFormat("mm:ss.SSS").format(time) : new SimpleDateFormat("ss.SSS").format(time);
    }
}
