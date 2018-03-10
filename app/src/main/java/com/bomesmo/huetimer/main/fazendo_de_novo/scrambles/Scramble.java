package com.bomesmo.huetimer.main.fazendo_de_novo.scrambles;

import java.util.Random;

/**
 * Created by Lucas on 31/12/2017.
 */

public abstract class Scramble {

    /**
     * Puzzle IDs.
     */
    public static final int RUBIKS_ID = 0;
    public static final int POCKET_ID = 1;
    public static final int SKEWB_ID = 2;
    public static final int CLOCK_ID = 3;

    /**
     * Variáveis úteis para construção dos embaralhamentos.
     */
    static final Random R = new Random(System.nanoTime());
    static final String[] POCKET_MOVES = {"R", "U", "F"};
    static final String[] SKEWB_MOVES = {"R", "L", "U", "B"};
    static final String[] RUBIKS_MOVES = {"Rx", "Uy", "Fz", "Lx", "Dy", "Bz"};
    static final String[] CLOCK_PINS = {"UR", "DR", "DL", "UL"};

    static final String[] CUBIC_DIRECTIONS = {" ", "' ", "2 "};
    static final String[] SKEWB_DIRECTIONS = {" ", "' "};

    /**
     * Método estático que retorna embaralhamentos de acordo com o ID.
     *
     * @param puzzleID ID do puzzle.
     * @return sequência de embaralhamento.
     */
    public static String getScrambleByID(int puzzleID) {
        Scramble r = null;

        switch (puzzleID) {
            case RUBIKS_ID:
                r = new Rubiks();
                break;

            case POCKET_ID:
                r = new Pocket();
                break;

            case SKEWB_ID:
                r = new Skewb();
                break;

            case CLOCK_ID:
                r = new Clock();
                break;
        }

        return r != null ? r.getSequence() : "";
    }

    /**
     * Método principal responsável por retornar a sequência principal do embaralhamento.
     *
     * @return embaralhamento.
     */
    public abstract String getSequence();
}
