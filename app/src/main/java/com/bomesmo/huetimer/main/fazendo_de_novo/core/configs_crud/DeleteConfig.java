package com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud;

import android.database.sqlite.SQLiteDatabase;

import com.bomesmo.huetimer.main.fazendo_de_novo.core.GlobalConfiguration;

/**
 * Created by Lucas on 09/03/2018.
 */

public class DeleteConfig {

    public void removerTabela() {
        SQLiteDatabase db = ConfigDB.getInstancia().getWritableDatabase();
        final String query = "DROP TABLE IF EXISTS " + ConfigDB.TABLE_CONFIG;
        db.execSQL(query);
    }

    public boolean removerConfig(GlobalConfiguration globalConfiguration) {
        SQLiteDatabase db = ConfigDB.getInstancia().getWritableDatabase();
        String where = "UUID = '" + globalConfiguration.getUuid() + "'";
        return db.delete(ConfigDB.TABLE_CONFIG, where, null) > 0;
    }
}
