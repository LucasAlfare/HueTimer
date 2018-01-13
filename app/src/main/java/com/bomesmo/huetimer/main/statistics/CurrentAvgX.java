package com.bomesmo.huetimer.main.statistics;

import com.bomesmo.huetimer.main.auxiliar.Solve;
import com.bomesmo.huetimer.main.auxiliar.TF;
import com.bomesmo.huetimer.main.statistics.auxiliar.Misc;

import java.util.ArrayList;

/**
 * Created by Lucas on 30/12/2017.
 */

public class CurrentAvgX extends Statistic {

    private int averageLength;

    public CurrentAvgX(ArrayList<Solve> solves, int averageLength) {
        super(solves);
        this.averageLength = averageLength;
    }

    @Override
    public String name() {
        return "Média de " + averageLength + " atual";
    }

    @Override
    public long result() {
        return solves.size() >= averageLength ?
                Misc.averageFrom(Misc.lastAverage(solves, averageLength)) : 0;
    }

    @Override
    public String details() {
        StringBuilder stringBuilder = new StringBuilder();

        if (solves.size() >= averageLength){
            Solve[] result = Misc.lastAverage(solves, averageLength);

            //loop reverso pois obtenho as solves de forma reversa, ai assim fica em ordem
            for (int i = result.length; i > 0; i--){
                stringBuilder.append(String.valueOf((result.length - i) + 1));
                stringBuilder.append(". ");

                stringBuilder.append(
                        Misc.isBest
                                (result[i - 1], result) ||
                                Misc.isWorst(result[i - 1], result) ?
                                "(" + TF.longToTimestamp(result[i - 1].getTime()) + ") " + result[i - 1].getScramble() + ";" :
                                result[i - 1]);

                stringBuilder.append("\n\n");
            }
        }

        return stringBuilder.toString();
    }
}
