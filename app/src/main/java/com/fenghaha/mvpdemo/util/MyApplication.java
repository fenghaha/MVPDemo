package com.fenghaha.mvpdemo.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by FengHaHa on2018/5/25 0025 12:13
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
