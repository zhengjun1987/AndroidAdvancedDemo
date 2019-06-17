package com.coindom.jpshdemo;

import android.app.Application;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;

import cn.jpush.android.api.JPushInterface;

public class JPushDemoApp extends Application {

    private JPushReceiver jPushReceiver;
    private static final String TAG = "JPushDemoApp";

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

//        读取设备硬件信息
        MyUtils.println(TAG, "android.os.Build.VERSION.SDK = " + Build.VERSION.SDK);
        MyUtils.println(TAG, "android.os.Build.MODEL = " + Build.MODEL);
        MyUtils.println(TAG, "android.os.Build.VERSION.RELEASE = " + Build.VERSION.RELEASE);
        MyUtils.println(TAG, "android.os.Build.MANUFACTURER = " + Build.MANUFACTURER);
        MyUtils.println(TAG, "Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID) = " + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
    }
}
