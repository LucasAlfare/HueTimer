package com.bomesmo.huetimer.main.scrambles;

import java.util.ArrayList;

/**
 * Created by Lucas on 31/12/2017.
 */

public class Clock extends Scramble {
    
    @Override
    public String getSequence() {
        StringBuilder r = new StringBuilder();
        int[] moves = {0, 1, 2, 3, 4, 5, 6};

        r.append("UR").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("DR").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("DL").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("UL").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));

        r.append("U").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("R").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("D").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("L").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));

        r.append("y2 ");
        r.append("\n");

        r.append("U").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("R").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("D").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));
        r.append("L").append(moves[R.nextInt(moves.length)])
                .append((r.toString().charAt(r.toString().length() - 1) != '0' ? (R.nextBoolean() ? "+ " : "- ") : " "));

        int pinsUp = R.nextInt(4);
        ArrayList<String> pins = new ArrayList<>();

        for (int i = 0; i < pinsUp; i++){
            String currPin = CLOCK_PINS[R.nextInt(CLOCK_PINS.length)];

            if (!pins.contains(currPin)) {
                pins.add(currPin);
                r.append(currPin).append(" ");
            }
        }

        return r.toString();
    }
}
