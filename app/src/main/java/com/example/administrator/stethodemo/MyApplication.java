package com.example.administrator.stethodemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * 作  者: roqy
 * 包  名: com.example.administrator.stethodemo
 * 日  期: 2018/6/29
 * 描  述:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
