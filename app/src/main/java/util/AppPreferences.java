package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreferences {

    public static void saveToPrefs(Context mContext, String key, String value) {
        SharedPreferences sharedPrefs;
        try {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);

            final SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(key, value);
            editor.apply();
        } catch (NullPointerException e) {
        }
    }

    public static void saveToPrefs(Context mContext, String key, boolean value) {
        SharedPreferences sharedPrefs;
        try {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);

            final SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (NullPointerException e) {
        }
    }

    public static String getFromPrefs(Context mContext, String key, String defaultValue) {
        SharedPreferences sharedPrefs;
        try {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);

            return sharedPrefs.getString(key, defaultValue);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static boolean getFromPrefs(Context mContext, String key, boolean defaultValue) {
        SharedPreferences sharedPrefs;
        try {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
            return sharedPrefs.getBoolean(key, defaultValue);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static void cleanPreferences(Context mContext) {
        SharedPreferences sharedPrefs;
        try {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
            sharedPrefs.edit().clear().apply();
        } catch (NullPointerException e) {
        }
    }
}
