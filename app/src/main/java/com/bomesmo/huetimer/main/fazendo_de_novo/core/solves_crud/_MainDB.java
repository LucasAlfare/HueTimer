package com.bomesmo.huetimer.main.fazendo_de_novo.core.solves_crud;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bomesmo.huetimer.main.MyApp;

/**
 * Created by Lucas on 07/03/2018.
 */

public class _MainDB extends SQLiteOpenHelper{

    private static String NOME_DB = "DB";
    private static int VERSAO_DB = 1;
    public static String TABLE_SOLVE = "TABLE_SOLVE";

    private static _MainDB instancia;

    public _MainDB() {
        super(MyApp.getContext(), NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static _MainDB getInstancia() {
        if (instancia == null){
            instancia = new _MainDB();
        }

        return instancia;
    }
}
