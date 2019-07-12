package com.coindom.jpshdemo;

import android.content.Context;

import java.util.ArrayList;

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

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("字符串" + (i + 1));
        }
        for (int i = 0; i < 15; i++) {
            String s;
            try {
                s = strings.get(i - 5);
            } catch (Exception e) {
                s = "0";
            }
            System.out.printf("%s ", s);
        }
    }
}
