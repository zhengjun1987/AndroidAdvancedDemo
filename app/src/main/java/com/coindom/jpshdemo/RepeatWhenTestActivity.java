package com.coindom.jpshdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class RepeatWhenTestActivity extends AppCompatActivity {
    private static final String TAG = "RepeatWhenTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_when_test);
        Observable.interval(1,TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Long aLong) throws Exception {
                        KLog.i(TAG, "RepeatWhenTestActivity.apply" + "  " + "aLong = [" + aLong + "]");
                        return Observable.just(String.valueOf(aLong));
                    }
                })
                .takeWhile(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        KLog.i(TAG, "RepeatWhenTestActivity.test" + "  " + "s = [" + s + "]");
                        return Long.valueOf(s)< 15;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        KLog.i(TAG, "RepeatWhenTestActivity.accept" + "  " + "s = [" + s + "]");
                    }
                });
    }
}
