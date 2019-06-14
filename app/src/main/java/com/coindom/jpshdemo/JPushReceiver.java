package com.coindom.jpshdemo;

import android.content.Context;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class JPushReceiver extends JPushMessageReceiver {
    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        System.out.println("context = [" + context + "], s = [" + s + "]");
    }

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        System.out.println("customMessage = [" + customMessage.toString() + "]");
    }
}
