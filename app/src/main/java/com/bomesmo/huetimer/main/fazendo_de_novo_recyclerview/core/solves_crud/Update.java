package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.Solve;

/**
 * Created by Lucas on 07/03/2018.
 */

public class Update {

    public boolean addSolve(Solve solve) {
        SQLiteDatabase db = _MainDB.getInstancia().getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("UUID", solve.getUuid());
        cv.put("GSONTIMES", solve.phasesTimesToJsonString());
        cv.put("SCRAMBLE", solve.getScramble());
        cv.put("ISDNF", solve.isDNF());
        cv.put("ISPLUSTWO", solve.isPlus2());

        return db.insert(_MainDB.TABLE_SOLVE, null, cv) != -1;
    }

    public boolean updateSolve(Solve solve) {
        SQLiteDatabase db = _MainDB.getInstancia().getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("UUID", solve.getUuid());
        cv.put("GSONTIMES", solve.phasesTimesToJsonString());
        cv.put("SCRAMBLE", solve.getScramble());
        cv.put("ISDNF", solve.isDNF());
        cv.put("ISPLUSTWO", solve.isPlus2());

        String where = "UUID = '" + solve.getUuid() + "'";

        return db.update(_MainDB.TABLE_SOLVE, cv, where, null) > 0;
    }
}
