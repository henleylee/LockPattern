package com.henleylee.lockpattern.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

/**
 * Toast工具类
 */
public class Utility {

    private static final String PREFERENCES_NAME = "config_pref";
    private static final String PATTERN_PASSWORD = "pattern_password";

    private static Toast sToast;

    @SuppressLint("ShowToast")
    public static void showToast(@NonNull Context context, @Nullable CharSequence text) {
        if (text == null || TextUtils.isEmpty(text)) {
            return;
        }
        int duration = Toast.LENGTH_SHORT;
        if (text.length() > 15) {
            duration = Toast.LENGTH_LONG;
        }
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        } else {
            sToast.setText(text);
        }
        sToast.show();
    }

    /**
     * 初始化ActionBar
     */
    public static void initActionBar(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);     // 设置是否标题/副标题(默认为true)
            actionBar.setHomeButtonEnabled(true);           // 设置是否启用左侧的图标是否可以点击(默认为false)
            actionBar.setDisplayShowHomeEnabled(true);      // 设置是否显示应用程序图标(默认为true)
            actionBar.setDisplayHomeAsUpEnabled(true);      // 设置是否给左侧添加一个返回的图标(默认为false)
        }
    }

    public static String getPatternPassword(@NonNull Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(PATTERN_PASSWORD, null);
    }

    public static void savePatternPassword(@NonNull Context context, @Nullable String password) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(PATTERN_PASSWORD, password)
                .apply();
    }

}
