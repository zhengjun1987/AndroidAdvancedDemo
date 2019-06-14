package com.coindom.jpshdemo;

import android.app.Application;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import cn.jpush.android.api.JPushInterface;

public class JPushDemoApp extends Application {

    private JPushReceiver jPushReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        jPushReceiver = new JPushReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(JPushInterface.ACTION_MESSAGE_RECEIVED);
        filter.addAction(JPushInterface.ACTION_NOTIFICATION_OPENED);
        filter.addAction(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
        filter.addAction(JPushInterface.ACTION_REGISTRATION_ID);
        filter.addAction(JPushInterface.ACTION_NOTIFICATION_RECEIVED);
        LocalBroadcastManager.getInstance(this).registerReceiver(jPushReceiver, filter);
    }
}
