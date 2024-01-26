package com.tapbi.spark.testzip.clock.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.SurfaceHolder;

import com.tapbi.spark.testzip.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Alarm {
    public int mHeight = 0;
    public int mWidth = 0;
    public int minAttr = 0;
    public Paint mPaintGradientRim =new Paint();

    public int mHandTruncation, mHourHandTruncation = 0;
    public int typeClock = -1;
    public Paint mPaintSecondWise = new Paint();
    public Paint mPaint = new Paint();
    public float px20 = 0f;
    public float px1 = 0f;
    public float px10 = 0f;
    public float centerX = 0f;
    public float centerY = 0f;

    public Paint mPrimaryPaint = new Paint();
    public Paint mSecondaryPaint = new Paint();

    public Paint mThirdPaint = new Paint();
    public int hour;
    public int minute;
    public int second;
    public Bitmap mBitmap;
    private Bitmap mBitmapBg;
    public Context context;

    public Calendar calendar;

    public Canvas canvas;
    public Boolean setTypeFont = false;
    public String typefaceNumber;
    public Paint mNumberPaint = new Paint();
    public int colorBackground = Color.WHITE;
    public Paint mPaintBackground = new Paint();
    private float topBmBg = 0f;
    public Canvas canvasTemp;
    private float leftBmBg = 0f;
    public int bmTempHaftWidth = 0;
    public int bmTemp1_3Width = 0;
    public float haftWidth = 0F;
    public int haftHeight = 0;
    public Rect mRect = new Rect();
    public int mRadius = 0;
    public Picture mPictureTemp = new Picture();
    public int bmTempHaftHeight = 0;
    public int radius = 0;
    public float leftBMTemp = 0f;
    public float topBMTemp = 0f;
    public int[] mClockHours12 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    public void setBitmapBgBase(Bitmap mBitmapbg) {
        if (mBitmapbg == null) {
            return;
        }
        mBitmapBg = mBitmapbg;
        leftBmBg = (mWidth - mBitmapBg.getWidth()) / 2f;
        topBmBg = (mHeight - mBitmapBg.getHeight()) / 2f;
    }

    public void setTypeFontNumberBase(String typeFontNumber, Context context) {
        typefaceNumber = typeFontNumber;
        try {
            if (setTypeFont) {
                Typeface type = Typeface.createFromAsset(context.getAssets(), typeFontNumber);
                mNumberPaint.setTypeface(type);
            }
        } catch (RuntimeException ignored) {
        }
    }

    public void setType(int type) {
        typeClock = type;
    }

    public void setValueStartDraw(
            Canvas mCanvas,
            SurfaceHolder holder,
            String timeZone
    ) {
        if (mCanvas != null) {
            canvas = mCanvas;
        } else if (holder != null) {
            canvas = holder.lockCanvas();
        }
        if (timeZone != null) {
            this.calendar = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
        } else this.calendar = Calendar.getInstance();
        //        millis = (int) SystemClock.uptimeMillis();
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        hour = calendar.get(Calendar.HOUR);
        if (hour > 12) {
            hour -= 12;
        }
        minAttr = Math.min(mHeight, mWidth);
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mHandTruncation = minAttr / 20;
            mHourHandTruncation = minAttr / 17;
            if (mBitmapBg != null) {
                canvas.drawBitmap(mBitmapBg, leftBmBg, topBmBg, null);
            } else {
                canvas.drawColor(context.getResources().getColor(R.color.clock));
            }
        }
    }

    public Float convertToPX(Float dip, Context context) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                context.getResources().getDisplayMetrics()
        );
    }
}
