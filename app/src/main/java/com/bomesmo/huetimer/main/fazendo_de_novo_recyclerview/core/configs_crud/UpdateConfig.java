package com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.configs_crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bomesmo.huetimer.main.fazendo_de_novo_recyclerview.core.GlobalConfiguration;

/**
 * Created by Lucas on 09/03/2018.
 */

public class UpdateConfig {

    public boolean addConfig(GlobalConfiguration globalConfiguration) {
        SQLiteDatabase db = ConfigDB.getInstancia().getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("UUID", globalConfiguration.getUuid().toString());
        cv.put("HOLDTIME", globalConfiguration.getHoldTime());
        cv.put("NUMPHASES", globalConfiguration.getNumPhases());
        cv.put("USEINSPECTION", globalConfiguration.isUseInspection());

        return db.insert(ConfigDB.TABLE_CONFIG, null, cv) != -1;
    }

    public boolean updateConfig(GlobalConfiguration globalConfiguration) {
        SQLiteDatabase db = ConfigDB.getInstancia().getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("UUID", globalConfiguration.getUuid().toString());
        cv.put("HOLDTIME", globalConfiguration.getHoldTime());
        cv.put("NUMPHASES", globalConfiguration.getNumPhases());
        cv.put("USEINSPECTION", globalConfiguration.isUseInspection());

        final String where = "UUID = '" + globalConfiguration.getUuid() + "'";

        return db.update(ConfigDB.TABLE_CONFIG, cv, where, null) > 0;
    }
}
