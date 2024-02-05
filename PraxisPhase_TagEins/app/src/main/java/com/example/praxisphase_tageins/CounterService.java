package com.example.praxisphase_tageins;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class CounterService extends Service {
    private final String LOG = "CounterService";
    private final IBinder addBinder = new CounterServiceBinder();
    private ExecutorService threadService;

    public class CounterServiceBinder extends Binder {
        public CounterService getService(){
            return CounterService.this;
        }
    }
    public void addData(String event){
        threadService.execute(() -> {
            //Write FileO
            Log.d(LOG,"Writing File: " + event);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.)
        });
    }
    @Override
    public void onCreate(){
        super.onCreate();
        threadService = Executors.newFixedThreadPool(10);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return addBinder;
    }

}
