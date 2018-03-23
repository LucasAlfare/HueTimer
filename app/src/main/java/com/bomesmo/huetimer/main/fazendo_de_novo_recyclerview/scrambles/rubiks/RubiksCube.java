package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.scrambles.rubiks;

import java.util.Arrays;

public class RubiksCube {

    private Sticker[] u = new Sticker[9];
    private Sticker[] d = new Sticker[9];

    private Sticker[] f = new Sticker[9];
    private Sticker[] b = new Sticker[9];

    private Sticker[] r = new Sticker[9];
    private Sticker[] l = new Sticker[9];

    private String sequence;

    public RubiksCube() {
        for (int i = 0; i < u.length; i++) {
            u[i] = Sticker.WHITE;
        }
        for (int i = 0; i < d.length; i++) {
            d[i] = Sticker.YELLOW;
        }

        for (int i = 0; i < f.length; i++) {
            f[i] = Sticker.GREEN;
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = Sticker.BLUE;
        }

        for (int i = 0; i < r.length; i++) {
            r[i] = Sticker.RED;
        }
        for (int i = 0; i < l.length; i++) {
            l[i] = Sticker.ORANGE;
        }
    }

    public RubiksCube(String sequence) {
        for (int i = 0; i < u.length; i++) {
            u[i] = Sticker.WHITE;
        }
        for (int i = 0; i < d.length; i++) {
            d[i] = Sticker.YELLOW;
        }

        for (int i = 0; i < f.length; i++) {
            f[i] = Sticker.GREEN;
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = Sticker.BLUE;
        }

        for (int i = 0; i < r.length; i++) {
            r[i] = Sticker.RED;
        }
        for (int i = 0; i < l.length; i++) {
            l[i] = Sticker.ORANGE;
        }

        this.applySequence(sequence);
    }

    public void applySequence(String sequence) {
        String[] moves = sequence.split(" ");

        for (String x : moves) {
            if (x.contains("U")) {
                if (x.contains("'")) u(3);
                else if (x.contains("2")) u(2);
                else u(1);
            }

            if (x.contains("D")) {
                if (x.contains("'")) d(3);
                else if (x.contains("2")) d(2);
                else d(1);
            }

            if (x.contains("F")) {
                if (x.contains("'")) f(3);
                else if (x.contains("2")) f(2);
                else f(1);
            }

            if (x.contains("B")) {
                if (x.contains("'")) b(3);
                else if (x.contains("2")) b(2);
                else b(1);
            }

            if (x.contains("R")) {
                if (x.contains("'")) {
                    r(3);
                } else if (x.contains("2")) {
                    r(2);
                } else {
                    r(1);
                }
            }

            if (x.contains("L")) {
                if (x.contains("'")) {
                    l(3);
                } else if (x.contains("2")) {
                    l(2);
                } else {
                    l(1);
                }
            }
        }
    }

    public void u(int count) {
        for (int i = 0; i < count; i++) u();
    }

    public void d(int count) {
        for (int i = 0; i < count; i++) d();
    }

    public void f(int count) {
        for (int i = 0; i < count; i++) f();
    }

    public void b(int count) {
        for (int i = 0; i < count; i++) b();
    }

    public void r(int count) {
        for (int i = 0; i < count; i++) r();
    }

    public void l(int count) {
        for (int i = 0; i < count; i++) l();
    }

    public void u() {
        Sticker[] auxU = new Sticker[u.length];
        System.arraycopy(u, 0, auxU, 0, auxU.length);

        u[0] = auxU[2];
        u[1] = auxU[5];
        u[2] = auxU[8];
        u[3] = auxU[1];
        u[5] = auxU[7];
        u[6] = auxU[0];
        u[7] = auxU[3];
        u[8] = auxU[6];

        Sticker[] ff = {r[2], r[1], r[0]};
        Sticker[] ll = {f[0], f[3], f[6]};
        Sticker[] bb = {l[6], l[7], l[8]};
        Sticker[] rr = {b[8], b[5], b[2]};

        f[0] = ff[0];
        f[3] = ff[1];
        f[6] = ff[2];
        l[6] = ll[0];
        l[7] = ll[1];
        l[8] = ll[2];
        b[8] = bb[0];
        b[5] = bb[1];
        b[2] = bb[2];
        r[2] = rr[0];
        r[1] = rr[1];
        r[0] = rr[2];
    }

    public void r() {
        Sticker[] auxR = new Sticker[r.length];
        System.arraycopy(r, 0, auxR, 0, auxR.length);

        r[0] = auxR[2];
        r[1] = auxR[5];
        r[2] = auxR[8];
        r[3] = auxR[1];
        r[5] = auxR[7];
        r[6] = auxR[0];
        r[7] = auxR[3];
        r[8] = auxR[6];

        Sticker[] uu = {f[6], f[7], f[8]};
        Sticker[] bb = {u[6], u[7], u[8]};
        Sticker[] dd = {b[6], b[7], b[8]};
        Sticker[] ff = {d[2], d[1], d[0]};

        u[6] = uu[0];
        u[7] = uu[1];
        u[8] = uu[2];
        b[6] = bb[0];
        b[7] = bb[1];
        b[8] = bb[2];
        d[2] = dd[0];
        d[1] = dd[1];
        d[0] = dd[2];
        f[6] = ff[0];
        f[7] = ff[1];
        f[8] = ff[2];
    }

    public void f() {
        Sticker[] auxF = new Sticker[f.length];
        System.arraycopy(f, 0, auxF, 0, auxF.length);

        f[0] = auxF[2];
        f[1] = auxF[5];
        f[2] = auxF[8];
        f[3] = auxF[1];
        f[5] = auxF[7];
        f[6] = auxF[0];
        f[7] = auxF[3];
        f[8] = auxF[6];

        Sticker[] uu = {l[8], l[5], l[2]};
        Sticker[] ll = {d[2], d[5], d[8]};
        Sticker[] dd = {r[2], r[5], r[8]};
        Sticker[] rr = {u[2], u[5], u[8]};

        u[8] = uu[0];
        u[5] = uu[1];
        u[2] = uu[2];
        l[2] = ll[0];
        l[5] = ll[1];
        l[8] = ll[2];
        d[2] = dd[0];
        d[5] = dd[1];
        d[8] = dd[2];
        r[2] = rr[0];
        r[5] = rr[1];
        r[8] = rr[2];
    }

    public void d() {
        Sticker[] auxD = new Sticker[d.length];
        System.arraycopy(d, 0, auxD, 0, auxD.length);

        d[0] = auxD[2];
        d[1] = auxD[5];
        d[2] = auxD[8];
        d[3] = auxD[1];
        d[5] = auxD[7];
        d[6] = auxD[0];
        d[7] = auxD[3];
        d[8] = auxD[6];

        Sticker[] ff = {l[0], l[1], l[2]};
        Sticker[] ll = {b[6], b[3], b[0]};
        Sticker[] bb = {r[8], r[7], r[6]};
        Sticker[] rr = {f[2], f[5], f[8]};

        f[2] = ff[0];
        f[5] = ff[1];
        f[8] = ff[2];
        l[0] = ll[0];
        l[1] = ll[1];
        l[2] = ll[2];
        b[6] = bb[0];
        b[3] = bb[1];
        b[0] = bb[2];
        r[8] = rr[0];
        r[7] = rr[1];
        r[6] = rr[2];
    }

    public void l() {
        Sticker[] auxL = new Sticker[l.length];
        System.arraycopy(l, 0, auxL, 0, auxL.length);

        l[0] = auxL[2];
        l[1] = auxL[5];
        l[2] = auxL[8];
        l[3] = auxL[1];
        l[5] = auxL[7];
        l[6] = auxL[0];
        l[7] = auxL[3];
        l[8] = auxL[6];

        Sticker[] uu = {b[0], b[1], b[2]};
        Sticker[] ff = {u[0], u[1], u[2]};
        Sticker[] dd = {f[0], f[1], f[2]};
        Sticker[] bb = {d[8], d[7], d[6]};

        u[0] = uu[0];
        u[1] = uu[1];
        u[2] = uu[2];
        f[0] = ff[0];
        f[1] = ff[1];
        f[2] = ff[2];
        d[8] = dd[0];
        d[7] = dd[1];
        d[6] = dd[2];
        b[0] = bb[0];
        b[1] = bb[1];
        b[2] = bb[2];
    }

    public void b() {
        Sticker[] auxB = new Sticker[b.length];
        System.arraycopy(b, 0, auxB, 0, auxB.length);

        b[0] = auxB[2];
        b[1] = auxB[5];
        b[2] = auxB[8];
        b[3] = auxB[1];
        b[5] = auxB[7];
        b[6] = auxB[0];
        b[7] = auxB[3];
        b[8] = auxB[6];

        Sticker[] uu = {r[0], r[3], r[6]};
        Sticker[] ll = {u[0], u[3], u[6]};
        Sticker[] dd = {l[0], l[3], l[6]};
        Sticker[] rr = {d[0], d[3], d[6]};

        u[0] = uu[0];
        u[3] = uu[1];
        u[6] = uu[2];
        l[0] = ll[0];
        l[3] = ll[1];
        l[6] = ll[2];
        d[0] = dd[0];
        d[3] = dd[1];
        d[6] = dd[2];
        r[0] = rr[0];
        r[3] = rr[1];
        r[6] = rr[2];
    }

    public Sticker[] getU() {
        return u;
    }

    public void setU(Sticker[] u) {
        this.u = u;
    }

    public Sticker[] getD() {
        return d;
    }

    public void setD(Sticker[] d) {
        this.d = d;
    }

    public Sticker[] getF() {
        return f;
    }

    public void setF(Sticker[] f) {
        this.f = f;
    }

    public Sticker[] getB() {
        return b;
    }

    public void setB(Sticker[] b) {
        this.b = b;
    }

    public Sticker[] getR() {
        return r;
    }

    public void setR(Sticker[] r) {
        this.r = r;
    }

    public Sticker[] getL() {
        return l;
    }

    public void setL(Sticker[] l) {
        this.l = l;
    }

    @Override
    public String toString() {
        String s = "";

        s += "u: " + Arrays.toString(u) + "\n\n";
        s += "d: " + Arrays.toString(d) + "\n\n";
        s += "f: " + Arrays.toString(f) + "\n\n";
        s += "b: " + Arrays.toString(b) + "\n\n";
        s += "r: " + Arrays.toString(r) + "\n\n";
        s += "l: " + Arrays.toString(l) + "\n\n";

        return s;
    }
}
