package com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bomesmo.huetimer.main.MyApp;

/**
 * Created by Lucas on 09/03/2018.
 */

public class ConfigDB extends SQLiteOpenHelper {

    public static final String NOME_DB = "DB_CONFIG";
    public static final int VERSAO_DB = 1;
    public static final String TABLE_CONFIG = "TABLE_CONFIG";

    private static ConfigDB instancia;

    private ConfigDB() {
        super(MyApp.getContext(), NOME_DB, null, VERSAO_DB);
    }

    public static ConfigDB getInstancia() {
        if (instancia == null) {
            instancia = new ConfigDB();
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
