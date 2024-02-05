package com.example.praxisphase_tageins.viewLayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.lifecycle.ViewModel;

import com.example.praxisphase_tageins.CounterService;

public class ServiceViewModel extends ViewModel {
    private CounterService counterservice;
    private CounterService.CounterServiceBinder counterBinder;
    private ServiceViewModel.ConnectionToAddService counterConn;

    public ServiceViewModel(){}
    public void initService(Context context){
        startCounterService(context);
    }
    private void startCounterService(Context context){
        counterConn = new ServiceViewModel.ConnectionToAddService();
        Intent intent = new Intent(context.getApplicationContext(),CounterService.class);
        context.bindService(intent,counterConn,Context.BIND_AUTO_CREATE);
    }
    public void stopCounterService(Context context){
        Intent intent = new Intent(context.getApplicationContext(),CounterService.class);
        context.stopService(intent);
    }

    public void logCount(String logData){
        if(counterservice != null){
            counterservice.addData(logData);
        }
    }
    class ConnectionToAddService implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            counterBinder = (CounterService.CounterServiceBinder) service;
            counterservice = counterBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
