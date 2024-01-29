package com.tapbi.spark.testzip.feature.weather;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.tapbi.spark.testzip.R;
import com.tapbi.spark.testzip.databinding.LayoutWeatherSmallBinding;
import com.tapbi.spark.testzip.feature.BaseLayoutWidget;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherSmall extends BaseLayoutWidget<LayoutWeatherSmallBinding> {
    private SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.ENGLISH);

    public WeatherSmall(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreateView(Context context) {
        initView(context);
        layoutParam();
    //    layoutParam(imgStatus, 100f, 100f);
    }

    private void initView(Context context) {
        tvDay = new TextView(context);
        tvDay.setId(View.generateViewId());
        tvTemp = new TextView(context);
        tvTemp.setId(View.generateViewId());
        tvTempL = new TextView(context);
        tvTempL.setId(View.generateViewId());
        tvTempH = new TextView(context);
        tvTempH.setId(View.generateViewId());
        imgStatus = new ImageView(context);
        imgStatus.setId(View.generateViewId());
        tvDay.setTypeface(Typeface.createFromAsset(context.getAssets(),"semi.ttf"));
        tvTemp.setTypeface(Typeface.createFromAsset(context.getAssets(),"semi.ttf"));
        tvTempL.setTypeface(Typeface.createFromAsset(context.getAssets(),"semi.ttf"));
        tvTempH.setTypeface(Typeface.createFromAsset(context.getAssets(),"semi.ttf"));
    }

    @Override
    public void initData() {
        tvDay.setText(format.format(System.currentTimeMillis()));
        tvDay.setTextSize(14);
        tvTemp.setText("20°");
        tvTemp.setTextSize(44);
        tvTemp.setTextColor(Color.WHITE);
        tvTempH.setText("20°/30°");
        tvTempH.setTextSize(14);
        imgStatus.setImageResource(R.drawable.vector);
        tvDay.setTextColor(Color.WHITE);
        tvTempH.setTextColor(Color.WHITE);
        ConstraintLayout layout = (ConstraintLayout)binding.constraintWeather;
        binding.constraintWeather.setBackgroundResource(R.drawable.test2);
        ConstraintSet set = new ConstraintSet();
        layout.addView(tvDay);
        layout.addView(tvTemp);
        layout.addView(tvTempH);
        layout.addView(tvTempL);
        layout.addView(imgStatus);
        set.clone(layout);
        set.connect(tvDay.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 60);
        set.connect(tvDay.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, 15);
        set.connect(tvTemp.getId(),ConstraintSet.TOP,tvDay.getId(),ConstraintSet.BOTTOM,1);
        set.connect(tvTemp.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, 15);
        set.connect(tvTempH.getId(),ConstraintSet.TOP,tvTemp.getId(),ConstraintSet.BOTTOM,1);
        set.connect(tvTempH.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, 15);
        set.connect(imgStatus.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END, 40);
        set.connect(imgStatus.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, 40);
        set.applyTo(layout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_weather_small;
    }

    private void layoutParam() {
    }
}
