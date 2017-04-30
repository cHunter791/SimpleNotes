package com.christopher.hunter.simplenotes;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Christopher on 30/04/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
