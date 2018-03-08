package com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bomesmo.huetimer.main.fazendo_de_novo.core.Solve;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Lucas on 07/03/2018.
 */

public class Read {

    public ArrayList<Solve> getSolves(){
        SQLiteDatabase db = _MainDB.getInstancia().getReadableDatabase();
        String query = "SELECT * FROM " + _MainDB.TABLE_SOLVE;

        ArrayList<Solve> solves = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Solve solve = new Solve(UUID.fromString(cursor.getString(0)));

                solve.setTime((ArrayList<Long>) new Gson().fromJson(
                        cursor.getString(1),
                        new TypeToken<ArrayList<Long>>(){}.getType()));

                solve.setScramble(cursor.getString(2));

                solve.setDNF(cursor.getInt(3) == 1);

                solve.setPlus2(cursor.getInt(4) == 1);

                solves.add(solve);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return solves;
    }
}
