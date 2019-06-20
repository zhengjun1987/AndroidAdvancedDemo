package com.coindom.jpshdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_stop;
    private Button btn_resume;
    private Button btn_detail;
    private LineChart test_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_stop = findViewById(R.id.btn_stop);
        btn_resume = findViewById(R.id.btn_resume);
        btn_detail = findViewById(R.id.btn_detail);
        test_chart = findViewById(R.id.test_chart);
        btn_stop.setOnClickListener(this);
        btn_resume.setOnClickListener(this);
        btn_detail.setOnClickListener(this);

        test_chart.setDrawBorders(true);
        test_chart.setBorderColor(R.color.colorAccent);
        test_chart.setBorderWidth(1);

        ArrayList<Entry> temperatures = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            temperatures.add(new Entry(i, (float) (Math.random() * 40)));
        }

        ArrayList<Entry> waterfalls = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Entry entry = new Entry(i, (float) (Math.random() * 1000));
            waterfalls.add(entry);
        }
        LineDataSet lineDataSet = new LineDataSet(waterfalls, "降水量");
        lineDataSet.setColor(Color.MAGENTA);
        lineDataSet.setMode(LineDataSet.Mode.STEPPED);
        LineDataSet lineDataSet1 = new LineDataSet(temperatures, "温度");
        lineDataSet1.setColor(Color.CYAN);
        LineData lineData = new LineData(lineDataSet,lineDataSet1);
        lineData.setValueTextSize(10);
        test_chart.setData(lineData);
        XAxis xAxis = test_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(waterfalls.size());
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "月";
            }
        });
//        test_chart.getAxisRight().setEnabled(false);
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
