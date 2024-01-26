package com.tapbi.spark.testzip;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestServices extends Service {
    private TimeChange timeChange;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timeChange = new TimeChange();
        registerTimeChange(timeChange);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() !=null){
            switch (intent.getAction()){
                case "TIME":
                    Log.d("Haibq", "onStartCommand:xxx ");
                case "TIME_AFTER":
                    Log.d("Haibq", "onStartCommand: ");
            }
        }

        return START_STICKY;
    }

    private void registerTimeChange(TimeChange timeChange) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        registerReceiver(timeChange, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timeChange != null) {
            unregisterReceiver(timeChange);
        }
    }
}
