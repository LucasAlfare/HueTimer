package com.bomesmo.huetimer.main.puzzles.scrambles;

/**
 * Created by Lucas on 31/12/2017.
 */

public class Skewb extends Scramble {
    @Override
    public String getSequence() {
        String last = "", curr = "";

        StringBuilder r = new StringBuilder();

        for (int i = 0; i < 10; i++){
            do {
                curr = SKEWB_MOVES[R.nextInt(SKEWB_MOVES.length)];
            } while (curr.equals(last));

            last = curr;

            r.append(curr).append(SKEWB_DIRECTIONS[R.nextInt(SKEWB_DIRECTIONS.length)]);
        }

        return r.toString();
    }
}
