package com.bomesmo.huetimer.main.auxiliar;


import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lucas on 24/12/2017.
 */

public class TF {

    /**
     * Método que formata um valor de tempo em milissegundos para {minutos:segundos.milissegundos}.
     *
     * @param time tempo em milissegnudos a ser formatado.
     * @return texto contendo o valor de {@time} formatado.
     */
    public static String format(long time){
        long minutes = Long.parseLong(new SimpleDateFormat("mm", Locale.US).format(time));
        return minutes > 0 ? new SimpleDateFormat("mm:ss.SSS", Locale.US).format(time) : new SimpleDateFormat("ss.SSS", Locale.US).format(time);
    }

    public static long stringToLong(String displayed){
        String[] nums = displayed.replaceAll("\\.", ":").split(":");

        long minutes = Long.parseLong(nums[0]) * 1000 * 10;
        long seconds = Long.parseLong(nums[1]) * 1000;
        long milisseconds = Long.parseLong(nums[2]);

        System.out.println(minutes);
        System.out.println(seconds);
        System.out.println(milisseconds);
        System.out.println();

        return 0;
    }

//    public static long stringToLong(String displayed){
//        String pattern;
//        if (displayed.contains(":") && displayed.contains(".")){
//            pattern = "mm:ss.SSS";
//        } else if (!displayed.contains(":") && displayed.contains(".")){
//            pattern = "ss.SSS";
//        } else {
//            pattern = "SSS";
//        }
//
//        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
//        Date date = null;
//
//        try {
//            date = formatter.parse(displayed);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return date != null ? date.getTime() : 0;
//    }

//    public static void main(String[] args) {
//        System.out.println(format(1000000));
//        System.out.println(stringToLong("10:00.000"));
//    }
}
