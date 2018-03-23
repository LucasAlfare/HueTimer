package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lucas on 07/03/2018.
 */

public class Create {

    public void criarTabela() {
        SQLiteDatabase db = _MainDB.getInstancia().getWritableDatabase();
        String colunas = "(UUID TEXT, GSONTIMES TEXT, SCRAMBLE TEXT, ISDNF INTEGER, ISPLUSTWO INTEGER)";
        String query = "CREATE TABLE IF NOT EXISTS " + _MainDB.TABLE_SOLVE + colunas;

        db.execSQL(query);
    }
}
