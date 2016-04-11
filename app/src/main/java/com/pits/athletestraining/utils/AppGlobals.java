package com.pits.athletestraining.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;


public class AppGlobals extends Application {

    private static Context sContext;
    private static SharedPreferences sPreferences;
    private static boolean freshLaunched = false;
    public static Typeface typefaceBold;
    public static Typeface typeface;

    @Override
    public void onCreate() {
        super.onCreate();
        freshLaunched = true;
        sContext = getApplicationContext();
        sPreferences = PreferenceManager.getDefaultSharedPreferences(sContext);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/DroidSerif.ttf");
        typefaceBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/DroidSerif-Bold.ttf");
    }

    public static Context getContext() {
        return sContext;
    }

    public static SharedPreferences getPreferences() {
        return sPreferences;
    }

    public static boolean isFreshLaunch() {
        return freshLaunched;
    }
}
