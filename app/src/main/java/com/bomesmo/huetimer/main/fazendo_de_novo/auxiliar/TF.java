package com.bomesmo.huetimer.main.fazendo_de_novo.auxiliar;

/**
 * Created by Lucas on 04/03/2018.
 */

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Lucas on 24/12/2017.
 */
public class TF {

    /**
     * Método que formata um valor de tempo em milissegundos para {minutos:segundos.milissegundos}.
     *
     * @param time tempo em milissegnudos a ser formatado.
     * @return texto contendo o valor de {@code time} formatado.
     */
    public static String longToTimestamp(long time){
        long minutes = Long.parseLong(new SimpleDateFormat("mm", Locale.US).format(time));
        return minutes > 0 ? new SimpleDateFormat("mm:ss.SSS", Locale.US).format(time) : new SimpleDateFormat("ss.SSS", Locale.US).format(time);
    }

    /**
     * Retorna um tempo no formato mm:ss.SSS para um valor do tipo {@code long}.
     *
     * @param displayed String contendo tempo no formato mm:ss.SSS ou ss.SSS.
     * @return valor total de {@code displayed} em milissegundos.
     */
    public static long timestampToLong(String displayed){
        String[] nums;
        long r = 0;

        if (displayed.contains(":")){
            nums = displayed.replaceAll("\\.", ":").split(":");
            r += Long.parseLong(nums[0]) * 60000;
            r += Long.parseLong(nums[1]) * 1000;
            r += Long.parseLong(nums[2]);
        } else {
            nums = displayed.split("\\.");
            r += Long.parseLong(nums[0]) * 1000;
            r += Long.parseLong(nums[1]);
        }

        return r;
    }
}