package com.tapbi.spark.testzip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeChange extends BroadcastReceiver {
    private int countTime = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case Intent.ACTION_TIME_TICK:
                    Log.d("Haibq", "onReceive: ");
                    updateTimeTick(context);
                case Intent.ACTION_TIME_CHANGED:
                    countTime = 0;
            }
        }
    }

    private void updateTimeTick(Context context) {
        if (countTime == -1 || countTime == 60) {
            Log.d("Haibq", "updateTimeTick: aa");
            countTime = 0;
            Intent intent=new Intent(context,TestServices.class);
            intent.setAction("TIME_AFTER");
            context.startService(intent);
        } else {
            Log.d("Haibq", "updateTimeTick: ");
            countTime++;
            Intent intent=new Intent(context,TestServices.class);
            intent.setAction("TIME");
            context.startService(intent);
        }
    }
}
