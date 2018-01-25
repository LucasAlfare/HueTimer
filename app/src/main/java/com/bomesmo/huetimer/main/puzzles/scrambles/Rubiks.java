package com.bomesmo.huetimer.main.puzzles.scrambles;

/**
 * Created by Lucas on 31/12/2017.
 */

public class Rubiks extends Scramble {
    @Override
    public String getSequence() {
        StringBuilder r = new StringBuilder();
        String a = "  ", b = "  ", c = "  ";

        for (int i = 0; i < 24; i++){
            do {
                c = RUBIKS_MOVES[R.nextInt(RUBIKS_MOVES.length)];
            } while (c.equals(b) || sameAxis(a, b, c));

            a = b;
            b = c;

            r.append(c.charAt(0)).append(CUBIC_DIRECTIONS[R.nextInt(CUBIC_DIRECTIONS.length)]);
        }

        return r.toString();
    }

    private boolean sameAxis(String x, String y, String z){
        return x.charAt(1) == y.charAt(1) && x.charAt(1) == z.charAt(1);
    }
}
