package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.solves_crud;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bomesmo.huetimer.main.MyApp;

/**
 * Created by Lucas on 07/03/2018.
 */

public class _MainDB extends SQLiteOpenHelper {

    public static final String TABLE_SOLVE = "TABLE_SOLVE";
    private static final String NOME_DB = "DB";
    private static final int VERSAO_DB = 1;
    private static _MainDB instancia;

    private _MainDB() {
        super(MyApp.getContext(), NOME_DB, null, VERSAO_DB);
    }

    public static _MainDB getInstancia() {
        if (instancia == null) {
            instancia = new _MainDB();
        }

        return instancia;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
