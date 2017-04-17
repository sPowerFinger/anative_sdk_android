package com.project.personal.csc.myapplication;

import android.app.Application;

import nativesdk.ad.adsdk.AdSdk;

/**
 * Created by hongwu on 3/10/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AdSdk.initialize(this, "27722");
    }
}
