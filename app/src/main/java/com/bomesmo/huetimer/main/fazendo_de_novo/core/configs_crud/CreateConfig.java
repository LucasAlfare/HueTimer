package com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lucas on 09/03/2018.
 */

public class CreateConfig {

    public void criarTabela() {
        SQLiteDatabase db = ConfigDB.getInstancia().getWritableDatabase();
        final String colunas = "(UUID TEXT, HOLDTIME INTEGER, NUMPHASES INTEGER, USEINSPECTION INTEGER)";
        final String query = "CREATE TABLE IF NOT EXISTS " + ConfigDB.TABLE_CONFIG + colunas;

        db.execSQL(query);
    }
}
