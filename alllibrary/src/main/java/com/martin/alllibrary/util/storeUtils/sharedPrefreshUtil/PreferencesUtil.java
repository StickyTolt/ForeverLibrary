package com.martin.alllibrary.util.storeUtils.sharedPrefreshUtil;

import android.content.Context;
import android.content.SharedPreferences;

import com.martin.alllibrary.ToolCache;

/**
 * 作者：Martin on 2017/7/25 09:57
 * 邮箱：martin0207mfh@163.com
 */
public class PreferencesUtil {

    private static PreferencesUtil instance = new PreferencesUtil();
    private static SharedPreferences preferences;

    private PreferencesUtil() {

    }

    public static PreferencesUtil getInstance() {
        if (preferences == null) {
            preferences = ToolCache.getContext().getSharedPreferences("driveData", Context.MODE_PRIVATE);
        }
        return instance;
    }

    public void saveStr(String key, String value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public String getStr(String key, String dValue) {
        return preferences.getString(key, dValue);
    }

    public int getInt(String key, int dValue) {
        return preferences.getInt(key, dValue);
    }

    public boolean getBoolean(String key, boolean dValue) {
        return preferences.getBoolean(key, dValue);
    }

}
