package com.coindom.jpshdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

    }

    override fun onResume() {
        super.onResume()
        println("KotlinActivity.onResume")
    }
}