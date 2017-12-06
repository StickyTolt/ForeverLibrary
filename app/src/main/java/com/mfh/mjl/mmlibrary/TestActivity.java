package com.mfh.mjl.mmlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();

    private int count = 0;
    boolean oneTrue = false;
    boolean twoTrue = false;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo01);

        init();
    }

    private void init() {

        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(0,"我是TreeMap");
        map.put(1,"我是TreeMap");
        map.put(5,"我是TreeMap");
        map.put(2,"我是TreeMap");
        map.put(4,"我是TreeMap");
        map.put(6,"我是TreeMap");
        map.put(3,"我是TreeMap");

        Log.e(TAG, "init:    " + map.toString() );
    }

    private class MyThread extends Thread {
        private int limit = 10;

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                if (limit > 0) {
                    limit -= 1;
                    count += 1;
                    Log.e(TAG, "run:  我是个买票的，这是第 " + i + " 张，总共卖出 " + count + "\n" + Thread.currentThread().getName());
                }
            }
        }
    }


    public void clickOne(View view) {
        count = 0;
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private class MyRunnable implements Runnable {
        private int limit = 10;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                if (limit > 0) {
                    limit -= 1;
                    count += 1;
                    Log.e(TAG, "run:  我是个买票的，这是第 " + i + " 张，总共卖出 " + count + "\n" + Thread.currentThread().getName());
                }
            }
        }
    }

    public void clickTwo(View view) {
        count = 0;
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    public void clickThree(View view) {
//        count = 0;
//        MyRunnable runnable = new MyRunnable();
//        new Thread(runnable, "第 1 家").start();
//        new Thread(runnable, "第 2 家").start();
//        new Thread(runnable, "第 3 家").start();

        ThreadDeadLock t1 = new ThreadDeadLock();      // 控制张三
        ThreadDeadLock t2 = new ThreadDeadLock();      // 控制李四
        t1.flag = true;
        t2.flag = false;
        Thread thA = new Thread(t1);
        Thread thB = new Thread(t2);
        thA.start();
        thB.start();

    }


    static class Zhangsan { // 定义张三类
        public void say() {
            Log.e(TAG, "say: 张三对李四说：“你给我画，我就把书给你。”");
        }

        public void get() {
            Log.e(TAG, "get: 张三得到画了。");
        }
    }

    ;

    static class Lisi { // 定义李四类
        public void say() {
            Log.e(TAG, "say: 李四对张三说：“你给我书，我就把画给你”");
        }

        public void get() {
            Log.e(TAG, "get: 李四得到书了。");
        }
    }

    public static class ThreadDeadLock implements Runnable {
        private static final Zhangsan zs = new Zhangsan();       // 实例化static型对象
        private static final Lisi ls = new Lisi();       // 实例化static型对象
        private boolean flag = false;  // 声明标志位，判断那个先说话

        public void run() {  // 覆写run()方法
            if (flag) {
                synchronized (zs) {   // 同步张三
                    zs.say();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (ls) {
                        zs.get();
                    }
                }
            } else {
                synchronized (ls) {
                    ls.say();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (zs) {
                        ls.get();
                    }
                }
            }
        }

    }
}
