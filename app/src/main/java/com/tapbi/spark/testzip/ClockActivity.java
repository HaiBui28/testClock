package com.tapbi.spark.testzip;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tapbi.spark.testzip.clock.AlarmView;
import com.tapbi.spark.testzip.databinding.ActivityClockBinding;

public class ClockActivity extends AppCompatActivity {
    private ActivityClockBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClockBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = new Intent(this, TestServices.class);
        startService(intent);
        AlarmView alarmView = new AlarmView(this);
        alarmView.init(1000, 1000);
        alarmView.setListenerClock(bitmapClock -> binding.imClock.setImageBitmap(bitmapClock));
    }
}