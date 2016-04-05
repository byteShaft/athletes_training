package com.pits.athletestraining.utils;

import android.content.SharedPreferences;

import static com.pits.athletestraining.utils.Constants.FIRST_RUN_KEY;

public class Helpers {

    public static boolean isRunningForTheFirstTime() {
        SharedPreferences prefs = AppGlobals.getPreferences();
        return prefs.getBoolean(FIRST_RUN_KEY, true);
    }

    public static void setIsRunningForTheFirstTime(boolean value) {
        SharedPreferences prefs = AppGlobals.getPreferences();
        prefs.edit().putBoolean(FIRST_RUN_KEY, value).apply();
    }
}
