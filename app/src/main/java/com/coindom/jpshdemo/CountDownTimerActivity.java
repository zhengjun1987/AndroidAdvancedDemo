package com.coindom.jpshdemo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;

public class CountDownTimerActivity extends AppCompatActivity {

    @BindView(R.id.tv_prompt)
    TextView tvPrompt;

    private static final String TAG = "CountDownTimerActivity";
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        ButterKnife.bind(this);

        countDownTimer = new CountDownTimer(25000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                KLog.i(TAG, "CountDownTimerActivity.onTick" + "  " + "millisUntilFinished = [" + millisUntilFinished + "]");
                long l = millisUntilFinished / 1000;
                tvPrompt.setText(l + "");
                if (l == 5) {
                    countDownTimer.cancel();
                }
            }

            @Override
            public void onFinish() {
                KLog.i(TAG, "CountDownTimerActivity.onFinish" + "  " + "");
                tvPrompt.setText("OVER!");
            }
        };
        countDownTimer.start();

//        Observable.timer(5, TimeUnit.SECONDS)
//                .flatMap(new Function<Long, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Long aLong) throws Exception {
//                          return Observable.just("5 SECONDS");
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<String>() {
//                    @Override
//                    protected void onStart() {
//                        super.onStart();
//                        KLog.i(TAG, "CountDownTimerActivity.onStart");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        KLog.i(TAG, "CountDownTimerActivity.onNext" + "  " + "s = [" + s + "]");
//                        tvPrompt.setText(s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        KLog.i(TAG, "CountDownTimerActivity.onComplete");
//                    }
//                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        countDownTimer.cancel();
    }
}
