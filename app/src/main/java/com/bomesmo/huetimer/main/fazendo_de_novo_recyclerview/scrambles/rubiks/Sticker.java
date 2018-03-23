package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.scrambles.rubiks;

import android.graphics.Color;

public enum Sticker {

    WHITE(Color.WHITE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    RED(Color.RED),
    ORANGE(Color.LTGRAY);

    private int color;

    Sticker(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
