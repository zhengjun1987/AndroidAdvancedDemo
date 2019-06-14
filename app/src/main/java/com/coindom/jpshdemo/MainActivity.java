package com.coindom.jpshdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_stop;
    private Button btn_resume;
    private Button btn_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_stop = findViewById(R.id.btn_stop);
        btn_resume = findViewById(R.id.btn_resume);
        btn_detail = findViewById(R.id.btn_detail);
        btn_stop.setOnClickListener(this);
        btn_resume.setOnClickListener(this);
        btn_detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stop:
                if (!JPushInterface.isPushStopped(MainActivity.this)) {
                    JPushInterface.stopPush(MainActivity.this);
                }
                break;
            case R.id.btn_resume:
                JPushInterface.resumePush(MainActivity.this);
                break;
            case R.id.btn_detail:
                break;
            default:
                break;
        }
    }
}
