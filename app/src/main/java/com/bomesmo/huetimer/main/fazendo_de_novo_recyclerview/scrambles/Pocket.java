package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.scrambles;

/**
 * Created by Lucas on 31/12/2017.
 */

public class Pocket extends Scramble {

    @Override
    public String getSequence() {
        String last = "", curr = "";

        StringBuilder r = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            do {
                curr = POCKET_MOVES[R.nextInt(POCKET_MOVES.length)];
            } while (curr.equals(last));

            last = curr;

            r.append(curr).append(CUBIC_DIRECTIONS[R.nextInt(CUBIC_DIRECTIONS.length)]);
        }

        return r.toString();
    }
}
