package com.example.startedservicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyStartedService extends Service {
    static final String TAG = "MyStartedService";

    public MyStartedService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service is started");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(1000);


                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                    Log.i(TAG, "Service is running " + Integer.toString(i));
                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service is destroyed");
    }
}