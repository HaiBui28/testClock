package com.tapbi.spark.testzip.clock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tapbi.spark.testzip.clock.base.Alarm;


public class AlarmView extends View {

    private Path pathClip;
    private Bitmap bitmap;
    private AlarmTest alarmTest;
    private ListenerClock listenerClock;

    private Alarm mAlarm;
    private int width;
    private int height;
    private Canvas canvas;

    public AlarmView(Context context) {
        super(context);
    }

    public AlarmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AlarmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListenerClock(ListenerClock mlistenerClock) {
        this.listenerClock = mlistenerClock;
    }

    private Handler handler;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("Haibq", "run: ");
            handler.removeCallbacks(this);
            Log.d("Haibq", "run: ddd");
            drawClock();
        }
    };

    public void init(int width, int height) {
        if (handler == null) {
            handler = new Handler();
        }
        Log.d("Haibq", "init: a");
        pathClip = new Path();
        pathClip.addRoundRect(
                0f,
                0f,
                width,
                height,
                width / 8f,
                height / 8f,
                Path.Direction.CCW
        );
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 0);
        Log.d("Haibq", "init: b");
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        this.width = width;
        this.height = height;
        alarmTest = null;
        mAlarm = new Alarm();
        if (width > 0) {
            alarmTest = new AlarmTest(width, height, getContext());
        }
        drawClock();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        drawClock();
    }

    private void draw(Canvas canvas1, String timeZone) {
        if (width > 0 && canvas1 != null) {
            if (pathClip != null) {
                canvas1.clipPath(pathClip);
            }
            canvas1.save();
            canvas1.save();
            if (alarmTest != null) {
                alarmTest.draw(null, canvas1, getContext(), 0f, timeZone);
            }

            canvas1.restore();
        }
    }

    private void drawClock() {
        draw(canvas, null);
        if (listenerClock != null){
            Log.d("Haibq", "drawClock: ");
            listenerClock.setUpBitmapClock(bitmap);
        }else {
            Log.d("Haibq", "drawClock: kkkk" );
        }

    }

    public interface ListenerClock {
        void setUpBitmapClock(Bitmap bitmapClock);
    }
}
