package com.coindom.jpshdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DefaultObserver;

public class RepeatWhenTestActivity extends AppCompatActivity {
    private static final String TAG = "RepeatWhenTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_when_test);
        KLog.i(TAG, "RepeatWhenTestActivity.onCreate" + "  " + "savedInstanceState = [" + savedInstanceState + "]");
        Observable.interval(1, TimeUnit.SECONDS).take(20)
                .flatMap(new Function<Long, ObservableSource<String>>() {

                    private Long aLong;

                    @Override
                    public ObservableSource<String> apply(Long aLong) throws Exception {
                        KLog.i(TAG, "RepeatWhenTestActivity.apply" + "  " + "aLong = [" + aLong + "]");
                        this.aLong = aLong;
                        return Observable.just(String.valueOf(aLong));
                    }
                })
                .takeWhile(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        KLog.i(TAG, "RepeatWhenTestActivity.test" + "  " + "s = [" + s + "]");
                        return Long.valueOf(s) < 25;
                    }
                })
                .subscribe(new DefaultObserver<String>() {
                    @Override
                    public void onNext(String aLong) {
                        KLog.i(TAG, "aLong = " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.i(TAG, "RepeatWhenTestActivity.onError" + "  " + "e = [" + e + "]");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        KLog.i(TAG, "RepeatWhenTestActivity.onComplete");
                    }
                });
    }
}
