package com.example.maptest.util;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolUtil {
    private Handler handler = new Handler();

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(7);

    public void queryData(final Work todo) {
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                todo.onInOhter();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        todo.onInMain();
                    }
                });
            }
        });
    }

    public interface Work {
        void onInOhter();

        String onInMain();
    }
}
