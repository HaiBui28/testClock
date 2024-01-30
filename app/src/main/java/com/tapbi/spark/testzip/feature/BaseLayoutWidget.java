package com.tapbi.spark.testzip.feature;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseLayoutWidget<B extends ViewDataBinding> extends FrameLayout {
    public TextView tvDay;
    public TextView tvTemp;
    public TextView tvTempH;
    public TextView tvTempL;
    public ImageView imgStatus;

    public B binding;

    public abstract int getLayoutId();

    public BaseLayoutWidget(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public BaseLayoutWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BaseLayoutWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public abstract void onCreateView(Context context);
    public abstract void initData(Context context);

    private void initView(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), this, true);
        onCreateView(context);
    }

}
