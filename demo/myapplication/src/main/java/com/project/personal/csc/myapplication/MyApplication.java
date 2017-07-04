package com.project.personal.csc.myapplication;

import android.app.Application;

import nativesdk.ad.common.AdSdk;


/**
 * Created by hongwu on 3/10/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AdSdk.initialize(this, "eic196h8765432b");
    }
}
