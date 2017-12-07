package com.mfh.mjl.mmlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.martin.alllibrary.base.BaseActivity;
import com.martin.alllibrary.util.showUtil.LogUtils;
import com.mfh.mjl.mmlibrary.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends BaseActivity {

    private static final String TAG = TestActivity.class.getSimpleName();

    public static void start(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        test();
    }

    private void test() {

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e(TAG, "subscribe: 当前线程 == " + Thread.currentThread().getName());
                Log.e(TAG, "subscribe: 111");
                e.onNext("111");
                Log.e(TAG, "subscribe: 222");
                e.onNext("222");
                Log.e(TAG, "subscribe: 333");
                e.onNext("333");
                Log.e(TAG, "subscribe: 444");
                e.onNext("444");
                Log.e(TAG, "subscribe: complete");
                e.onComplete();
            }
        });

        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e(TAG, "subscribe:  -！- 当前线程 == " + Thread.currentThread().getName());
                Log.e(TAG, "subscribe -!- : aaa");
                e.onNext("aaa");
                Thread.sleep(1000);
                Log.e(TAG, "subscribe -!- : bbb");
                e.onNext("bbb");
                Thread.sleep(1000);
                Log.e(TAG, "subscribe -!- : ccc");
                e.onNext("ccc");
                Thread.sleep(1000);
                Log.e(TAG, "subscribe -!- : ddd");
                e.onNext("ddd");
                Thread.sleep(1000);
                Log.e(TAG, "subscribe -!- : eee");
                e.onNext("eee");
                Thread.sleep(1000);
                Log.e(TAG, "subscribe: -!- complete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());


        Observable.zip(observable1, observable, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                Log.e(TAG, "apply: 当前线程 " + Thread.currentThread().getName());
                return s + "+" + s2;
            }
        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });

        LogUtils.e("这个东西，最后执行");

    }
}
