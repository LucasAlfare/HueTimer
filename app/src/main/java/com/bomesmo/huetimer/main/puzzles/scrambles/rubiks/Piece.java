package com.bomesmo.huetimer.main.puzzles.scrambles.rubiks;

import java.util.Arrays;

public enum Piece {

    A(PieceType.CORNER, Sticker.WHITE, Sticker.ORANGE, Sticker.BLUE),
    B(PieceType.EDGE, Sticker.WHITE, Sticker.BLUE),
    C(PieceType.CORNER, Sticker.WHITE, Sticker.BLUE, Sticker.RED),

    D(PieceType.EDGE, Sticker.WHITE, Sticker.ORANGE),
    E(PieceType.CENTER, Sticker.WHITE),
    F(PieceType.EDGE, Sticker.WHITE, Sticker.RED),

    G(PieceType.CORNER, Sticker.WHITE, Sticker.GREEN, Sticker.ORANGE),
    H(PieceType.EDGE, Sticker.WHITE, Sticker.GREEN),
    I(PieceType.CORNER, Sticker.WHITE, Sticker.RED, Sticker.GREEN),


    J(PieceType.EDGE, Sticker.ORANGE, Sticker.BLUE),
    K(PieceType.CENTER, Sticker.BLUE),
    L(PieceType.EDGE, Sticker.BLUE, Sticker.RED),

    M(PieceType.CENTER, Sticker.ORANGE),
    N(PieceType.CENTER, Sticker.RED),

    O(PieceType.EDGE, Sticker.ORANGE, Sticker.GREEN),
    P(PieceType.CENTER, Sticker.GREEN),
    Q(PieceType.EDGE, Sticker.GREEN, Sticker.RED),


    R(PieceType.CORNER, Sticker.YELLOW, Sticker.ORANGE, Sticker.GREEN),
    S(PieceType.EDGE, Sticker.YELLOW, Sticker.GREEN),
    T(PieceType.CORNER, Sticker.YELLOW, Sticker.GREEN, Sticker.RED),

    U(PieceType.EDGE, Sticker.YELLOW, Sticker.ORANGE),
    V(PieceType.CENTER, Sticker.YELLOW),
    W(PieceType.EDGE, Sticker.YELLOW, Sticker.RED),

    X(PieceType.CORNER, Sticker.YELLOW, Sticker.BLUE, Sticker.ORANGE),
    Y(PieceType.EDGE, Sticker.YELLOW, Sticker.BLUE),
    Z(PieceType.CORNER, Sticker.YELLOW, Sticker.RED, Sticker.BLUE);

    private PieceType pieceType;
    private Sticker[] stickers;

    Piece(PieceType pieceType, Sticker... stickers) {
        this.pieceType = pieceType;
        this.stickers = stickers;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Sticker[] getStickers() {
        return stickers;
    }

    public void setStickers(Sticker[] stickers) {
        this.stickers = stickers;
    }

    @Override
    public String toString() {
        return Arrays.toString(getStickers());
    }
}
