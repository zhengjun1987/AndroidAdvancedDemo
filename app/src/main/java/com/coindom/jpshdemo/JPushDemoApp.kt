package com.coindom.jpshdemo

import android.app.Activity
import android.app.Application
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.content.LocalBroadcastManager

import com.socks.library.KLog

import cn.jpush.android.api.JPushInterface

class JPushDemoApp : Application() {

    private var jPushReceiver: JPushReceiver? = null
    val activityListener = object : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
            println("MyApp.onActivityPaused " + "activity = [${activity}]")
        }

        override fun onActivityResumed(activity: Activity?) {
            println("MyApp.onActivityResumed " + "activity = [${activity}]")
        }

        override fun onActivityStarted(activity: Activity?) {
            println("MyApp.onActivityStarted " + "activity = [${activity}]")
        }

        override fun onActivityDestroyed(activity: Activity?) {
            println("MyApp.onActivityDestroyed " + "activity = [${activity}]")
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            println("MyApp.onActivitySaveInstanceState " + "activity = [${activity}], outState = [${outState}]")
        }

        override fun onActivityStopped(activity: Activity?) {
            println("MyApp.onActivityStopped " + "activity = [${activity}]")
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            println("MyApp.onActivityCreated " + "activity = [${activity}], savedInstanceState = [${savedInstanceState}]")
        }
    }

    override fun onCreate() {
        super.onCreate()
        KLog.init(BuildConfig.KLOG_DEBUG, "zhengjun")
        registerActivityLifecycleCallbacks(activityListener)
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        jPushReceiver = JPushReceiver()
        val filter = IntentFilter()
        filter.addAction(JPushInterface.ACTION_MESSAGE_RECEIVED)
        filter.addAction(JPushInterface.ACTION_NOTIFICATION_OPENED)
        filter.addAction(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY)
        filter.addAction(JPushInterface.ACTION_REGISTRATION_ID)
        filter.addAction(JPushInterface.ACTION_NOTIFICATION_RECEIVED)
        LocalBroadcastManager.getInstance(this).registerReceiver(jPushReceiver!!, filter)

        //        读取设备硬件信息
        MyUtils.println(TAG, "android.os.Build.VERSION.SDK = " + Build.VERSION.SDK)
        MyUtils.println(TAG, "android.os.Build.MODEL = " + Build.MODEL)
        MyUtils.println(TAG, "android.os.Build.VERSION.RELEASE = " + Build.VERSION.RELEASE)
        MyUtils.println(TAG, "android.os.Build.MANUFACTURER = " + Build.MANUFACTURER)
        MyUtils.println(TAG, "Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID) = " + Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))
    }

    override fun onLowMemory() {
        super.onLowMemory()
        unregisterActivityLifecycleCallbacks(activityListener)
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterActivityLifecycleCallbacks(activityListener)
    }

    companion object {
        private val TAG = "JPushDemoApp"
    }
}
