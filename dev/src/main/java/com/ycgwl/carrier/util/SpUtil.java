package com.ycgwl.carrier.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    public SharedPreferences sp;
    private static SpUtil instance;
    public final SharedPreferences.Editor edit;
    private static String FILE_NAME = "wlksphone";
    private static final String IS_UPDATE = "update";

    protected SpUtil(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    public static synchronized SpUtil getInstance() {
        if (instance == null) {
            instance = new SpUtil(Utils.getContext());
        }
        return instance;
    }

    public SpUtil putInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int dValue) {
        return sp.getInt(key, dValue);
    }

    public SpUtil putLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
        return this;
    }

    public long getLong(String key, Long dValue) {
        return sp.getLong(key, dValue);
    }

    public SpUtil putFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
        return this;
    }

    public Float getFloat(String key, Float dValue) {
        return sp.getFloat(key, dValue);
    }

    public SpUtil putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
        return this;
    }

    public Boolean getBoolean(String key, boolean dValue) {
        return sp.getBoolean(key, dValue);
    }

    public SpUtil putString(String key, String value) {
        sp.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String dValue) {
        return sp.getString(key, dValue);
    }

    public void remove(String key) {
        if (isExist(key)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    public boolean isExist(String key) {
        return sp.contains(key);
    }

    //保存token
    public static void setToken(String passwd) {
        getInstance().edit.putString("token", passwd);
        getInstance().edit.commit();
    }

    public String getToken() {
        return getInstance().sp.getString("token", "");
    }

    public boolean getIsUpdate() {
        return getInstance().sp.getBoolean(IS_UPDATE, false);
    }

    public void setIsUpdate(boolean isUpdate) {
        getInstance().edit.putBoolean(IS_UPDATE, isUpdate).commit();
    }
}