package com.example.to_dolist.core.database;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedPref {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static SharedPref instance;
    private final SharedPreferences sharedPreferences;
    private static final String KEY_ORDER = "order";
    private static final String KEY_MODE = "mode";
    private SharedPref(Context context) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }
    public static synchronized SharedPref getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPref(context);
        }
        return instance;
    }
    public void setOrder(String order) {
        sharedPreferences.edit().putString(KEY_ORDER, order).apply();
    }
    public void setMode(String mode) {
        sharedPreferences.edit().putString(KEY_MODE, mode).apply();
    }
    public String getOrder() {
        return sharedPreferences.getString(KEY_ORDER, "asc"); // default asc
    }
    public String getMode() {
        return sharedPreferences.getString(KEY_MODE, "light"); // default light
    }
}