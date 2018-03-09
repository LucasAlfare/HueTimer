package com.bomesmo.huetimer.main.fazendo_de_novo.core.configs_crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bomesmo.huetimer.main.fazendo_de_novo.core.GlobalConfiguration;

import java.util.UUID;

/**
 * Created by Lucas on 09/03/2018.
 */

public class ReadConfig {

    public GlobalConfiguration getGlobalConfiguration() {
        SQLiteDatabase db = ConfigDB.getInstancia().getReadableDatabase();
        final String query = "SELECT * FROM " + ConfigDB.TABLE_CONFIG;

        GlobalConfiguration r = null;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            r = new GlobalConfiguration();
            do {
                r.setUuid(UUID.fromString(cursor.getString(0)));
                r.setHoldTime(cursor.getInt(1));
                r.setNumPhases(cursor.getInt(2));
                r.setUseInspection(cursor.getInt(3) == 1);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return r;
    }
}
