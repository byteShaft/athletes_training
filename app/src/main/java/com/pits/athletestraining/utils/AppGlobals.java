package com.pits.athletestraining.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class AppGlobals extends Application {

    private static Context sContext;
    private static SharedPreferences sPreferences;
    private static boolean freshLaunched = false;

    @Override
    public void onCreate() {
        super.onCreate();
        freshLaunched = true;
        sContext = getApplicationContext();
        sPreferences = PreferenceManager.getDefaultSharedPreferences(sContext);
    }

    public static Context getContext() {
        return sContext;
    }

    public static SharedPreferences getPreferences() {
        return sPreferences;
    }

    public static boolean isFreshLaunched() {
        return freshLaunched;
    }

    public static void firstLaunch(boolean value) {
        freshLaunched = true;
    }
}
