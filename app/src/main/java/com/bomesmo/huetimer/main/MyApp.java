package com.bomesmo.huetimer.main;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lucas on 07/03/2018.
 */

public class MyApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
