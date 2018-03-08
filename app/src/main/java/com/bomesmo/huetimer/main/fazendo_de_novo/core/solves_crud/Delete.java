package com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud;

import android.database.sqlite.SQLiteDatabase;

import com.bomesmo.huetimer.main.fazendo_de_novo.core.Solve;

/**
 * Created by Lucas on 07/03/2018.
 */
public class Delete {

    public void removerTabela(){
        SQLiteDatabase db = _MainDB.getInstancia().getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + _MainDB.TABLE_SOLVE;
        db.execSQL(query);
    }

    public boolean removerSolve(Solve solve){
        SQLiteDatabase db = _MainDB.getInstancia().getWritableDatabase();

        String query = "UUID = '" + solve.getUuid() + "'";

        return db.delete(_MainDB.TABLE_SOLVE, query, null) > 0;
    }
}
