package com.tapbi.spark.testzip.feature.weather;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.tapbi.spark.testzip.R;
import com.tapbi.spark.testzip.model.ImageViewModel;
import com.tapbi.spark.testzip.model.TextViewModel;
import com.tapbi.spark.testzip.databinding.LayoutWeatherSmallBinding;
import com.tapbi.spark.testzip.feature.BaseLayoutWidget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class WeatherSmall extends BaseLayoutWidget<LayoutWeatherSmallBinding> {
    private ArrayList<Object> list;
    private ArrayList<ImageViewModel> listImage;
    private SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.ENGLISH);

    public WeatherSmall(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreateView(Context context) {
        list = new ArrayList<>();
        listImage = new ArrayList<>();
        listImage.add(new ImageViewModel(R.drawable.vector, 74, 75));
        list.add(new TextViewModel("Friday", "#FFFFFF", 14, 8 / 155f, 16 / 155f));
        list.add(new TextViewModel("26°","#FFFFFF",44,8/155f,34/155f));
//        list.add(new TextViewModel("20°/30°","#FFFFFF",19,84,8));
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
        tvDay.setTypeface(Typeface.createFromAsset(context.getAssets(), "semi.ttf"));
        tvTemp.setTypeface(Typeface.createFromAsset(context.getAssets(), "semi.ttf"));
        tvTempL.setTypeface(Typeface.createFromAsset(context.getAssets(), "semi.ttf"));
        tvTempH.setTypeface(Typeface.createFromAsset(context.getAssets(), "semi.ttf"));
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initData(getContext());
    }

    @Override
    public void initData(Context context) {
        if (getWidth() == 0 || getHeight() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof TextViewModel) {
                addText(context, (TextViewModel) list.get(i));
            } else if (list.get(i) instanceof ImageViewModel) {
//                addI
            }
        }
//        tvDay.setText(format.format(System.currentTimeMillis()));
//        tvDay.setTextSize(14);
//        tvTemp.setText("20°");
//        tvTemp.setTextSize(44);
//        tvTemp.setTextColor(Color.WHITE);
//        tvTempH.setText("20°/30°");
//        tvTempH.setTextSize(14);
//        imgStatus.setImageResource(R.drawable.vector);
//        tvDay.setTextColor(Color.WHITE);
//        tvTempH.setTextColor(Color.WHITE);
//        ConstraintLayout layout = (ConstraintLayout)binding.constraintWeather;
        binding.constraintWeather.setBackgroundResource(R.drawable.test2);
//        ConstraintSet set = new ConstraintSet();
//        layout.addView(tvDay);
//        layout.addView(tvTemp);
//        layout.addView(tvTempH);
//        layout.addView(tvTempL);
//        layout.addView(imgStatus);
//        set.clone(layout);
//        set.connect(tvDay.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, dpToPixels(16));
//        set.connect(tvDay.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, dpToPixels(8));
//        set.connect(tvTemp.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,dpToPixels(30));
//        set.connect(tvTemp.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, dpToPixels(8));
//        set.connect(tvTempH.getId(),ConstraintSet.TOP,layout.getId(),ConstraintSet.TOP,dpToPixels(84));
//        set.connect(tvTempH.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, dpToPixels(8));
//        set.connect(imgStatus.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, dpToPixels(74));
//        set.connect(imgStatus.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, dpToPixels(75));
//        set.applyTo(layout);
    }

    public int dpToPixels(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_weather_small;
    }

    private void layoutParam() {
    }

    private void addText(Context context, TextViewModel textViewModel) {
        TextView textView = new TextView(context);
        textView.setId(View.generateViewId());
        textView.setText(textViewModel.getContent());
        textView.setTextColor(Color.parseColor(textViewModel.getTextColor()));
        textView.setTextSize(textViewModel.getTextSize());
//            ConstraintLayout layout = (ConstraintLayout)binding.constraintWeather;
        Log.d("Haibq", "addText: " + getWidth() + ":" + getHeight());
        textView.setTranslationX(getWidth() * textViewModel.getRatioLeft());
        textView.setTranslationY(getHeight() * textViewModel.getRatioTop());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        binding.constraintWeather.addView(textView, params);
//            ConstraintSet set = new ConstraintSet();
//            layout.addView(textView);
//            set.clone(layout);
//            set.connect(textView.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, dpToPixels(list.get(i).getMarginTop()));
//            set.connect(textView.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, dpToPixels(list.get(i).getGetMarginLeft()));
//            set.applyTo(layout);
    }

//    private void addImage(Context context,) {
//
//    }
}
