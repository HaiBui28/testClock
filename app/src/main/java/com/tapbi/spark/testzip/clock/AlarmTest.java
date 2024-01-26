package com.tapbi.spark.testzip.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.util.Log;
import android.view.SurfaceHolder;

import com.tapbi.spark.testzip.clock.base.Alarm;

public class AlarmTest extends Alarm {
    private Paint mPaintGradientRim = new Paint();
    private Paint mPaintGradientText = new Paint();
    private Path path = new Path();
    private Paint mPaintCircleBlur = new Paint();
    private int[] color16 =
            {Color.parseColor("#4D070707"), Color.TRANSPARENT, Color.TRANSPARENT};
    private Paint paintGradient16 = new Paint();
    private SweepGradient mSweepGradient16;
    private int[] colorGradient = {
            Color.parseColor("#99C17D00"),
            Color.parseColor("#FFBD43"),
            Color.parseColor("#FD1F59"),
            Color.parseColor("#E40FE9"),
            Color.parseColor("#3C66B8"),
            Color.parseColor("#43B857"),
            Color.parseColor("#99C17D00")
    };
    private final int[] coloGradientFrame = {
            Color.parseColor("#FFF100"),
            Color.parseColor("#00CEFB"),
            Color.parseColor("#FF00E3"),
            Color.parseColor("#FE0000"),
            Color.parseColor("#FFF100")
    };

    public AlarmTest(int width, int height, Context context) {
        setTypeClock(width, height, context);
        init();
    }


    private void init() {
        mPrimaryPaint.setStrokeCap(Paint.Cap.ROUND);
        mSecondaryPaint.setStrokeCap(Paint.Cap.ROUND);
        mThirdPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaintGradientRim.setAntiAlias(true);
        mPaintGradientRim.setStyle(Paint.Style.FILL);
        mPaintGradientRim.setStrokeCap(Paint.Cap.ROUND);
        mPaintGradientRim.setShader(new SweepGradient(centerX,centerY,coloGradientFrame,null));
    }

    private void setTypeClock(int width, int height, Context context) {
        this.context = context;
        px20 = convertToPX(20f, context);
        //size dot center and minute clock;
        px1 = convertToPX(1f, context);
        // size hour
        px10 = convertToPX(10f, context);
        // full color instead of outline
        mSecondaryPaint.setStyle(Paint.Style.FILL);
        mSecondaryPaint.setAntiAlias(true);
        mThirdPaint.setStyle(Paint.Style.FILL);
        mThirdPaint.setAntiAlias(true);
        mPrimaryPaint.setStyle(Paint.Style.FILL);
        mPrimaryPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaintSecondWise.setStyle(Paint.Style.FILL);
        mPaintSecondWise.setAntiAlias(true);
        mNumberPaint.setAntiAlias(true);
//        getPin(context)
        if (width > 0) {
            mHeight = height;
            mWidth = width;
            haftWidth = ((float) mWidth / 2);
            bmTempHaftWidth = mWidth / 2;
            mRadius = (int) (bmTempHaftWidth - px20);
            centerX = mWidth / 2.0f;
            centerY = mHeight / 2.0f;
        }
    }

    private void drawRoseTypeForTheme() {
        Paint paint =mPaintGradientRim;
        mPaintBackground.setColor(colorBackground);
        canvasTemp.drawCircle(centerX, centerY, haftWidth, mPaintBackground);
        drawNumber4x(
                (haftWidth / 10),
                (haftWidth / 3f),
                mClockHours12,
                (haftWidth / 1.5f),
                mRect,
                mWidth,
                mHeight,
                mRadius,
                haftWidth / 8f,
                canvasTemp
        );
        drawRimBigGradient(paint);
        drawTabGradient(paint);
        //draw hour
        canvasTemp.save();
        mNumberPaint.setColor(Color.RED);
        canvasTemp.rotate(hour * 30.0f + minute / 60.0f * 30.0f, centerX, centerY);
        mPrimaryPaint.setColor(Color.parseColor("#797D51"));
        mPrimaryPaint.setStrokeWidth(px10 * 3);
        canvasTemp.drawLine(
                centerX,
                centerY,
                centerX,
                centerY - (bmTempHaftWidth - px10 * 8),
                mPrimaryPaint
        );
        canvasTemp.restore();

        //draw minute
        canvasTemp.save();
        canvasTemp.rotate(6.0f * minute + second / 60.0f * 6.0f, centerX, centerY);
        mSecondaryPaint.setColor(Color.YELLOW);
        mSecondaryPaint.setStrokeWidth(px1 * 8);
        canvasTemp.drawLine(
                centerX,
                centerY,
                centerX,
                centerY - (bmTempHaftWidth - px10 * 3),
                mSecondaryPaint
        );
        canvasTemp.restore();
        canvasTemp.save();
        Log.d("Haibq", "drawRoseTypeForTheme: " + second);
        canvasTemp.rotate(second * 6.0f, centerX, centerY);
        mThirdPaint.setColor(Color.RED);
        mThirdPaint.setStrokeWidth(px1 * 5);
        canvasTemp.drawLine(centerX, centerY, centerX, centerY - (bmTempHaftWidth - px10 * 3), mThirdPaint);
        canvasTemp.restore();
        canvasTemp.drawCircle(centerX, centerY, px1 * 7, mPaintSecondWise);
    }

    public void drawNumber4x(float n, float textSize, int[] mClockHours12,
                             float px_rommanSize, Rect mRect,
                             float mWidth, float mHeight, int mRadius, float minus, Canvas canvas
    ) {
        mNumberPaint.setColor(Color.RED);
        int x;
        int y;
        for (int hour : mClockHours12) {
            String tmp = "";
            if (hour == 3 || hour == 6 || hour == 9 || hour == 12) {
                mNumberPaint.setTextSize(textSize);
                tmp = String.valueOf(hour);
                mNumberPaint.getTextBounds(tmp, 0, tmp.length(), mRect);
                double angle = Math.PI / 6 * (hour - 3);
                x = (int) (mWidth / 2 + Math.cos(angle) * (mRadius - n - minus) - mRect.width() / 2);
                y = (int) (mHeight / 2 + Math.sin(angle) * (mRadius - n - minus) + mRect.height() / 2);
                canvas.drawText(String.valueOf(hour), x, y, mNumberPaint);
            }

        }
    }

    public void draw(SurfaceHolder holder, Canvas mCanvas, Context mContext, Float yTranslate, String timeZone) {
        this.context = mContext;
        if (mWidth > 0) {
            try {
                mPictureTemp.endRecording();
                canvasTemp = mPictureTemp.beginRecording(mWidth, mHeight);
                setValueStartDraw(mCanvas, holder, timeZone);
                canvasTemp.drawColor(Color.TRANSPARENT);
                canvasTemp.translate(0F, yTranslate);
                canvasTemp.save();
                canvasTemp.translate(0F, mHeight / 5.5f);
                canvasTemp.scale(1.2f, 1.2f, mWidth / 2f, mHeight / 2f);
                canvasTemp.scale(0.7f, 0.7f, haftWidth, 0f);
                drawRoseTypeForTheme();
                canvas.restore();
                mPictureTemp.endRecording();
                if (canvas != null) {
                    canvas.drawPicture(mPictureTemp);
                }
            } finally {
                if (canvas != null) {
//                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void drawRimBigGradient(Paint paint) {
        paint.setAlpha(255);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(px1 * 10);
        SweepGradient sweepGradient = new SweepGradient(centerX,centerY,coloGradientFrame,null);
        for (int i = 0; i < 60; i++) {
            Log.d("Haibq", "drawRimBigGradient: ");
            canvasTemp.rotate(6f,centerX,centerY);
            if (i == 59 || i == 14 || i== 29 || i == 44 || i == 4 || i == 9 || i == 19 || i == 24 || i == 34 || i == 39 || i == 49 || i == 54){
                Matrix gradientMatrix = new Matrix();
                gradientMatrix.preRotate((i + 1) * -6f, centerX, centerY);
                sweepGradient.setLocalMatrix(gradientMatrix);
                paint.setShader(sweepGradient);
                canvasTemp.drawLine(centerX, (centerY - bmTempHaftWidth + px1 * 26), centerX, (centerY - bmTempHaftWidth + px1 * 14),
                        paint);
            }else {
                Matrix gradientMatrix = new Matrix();
                mPaintGradientRim.setStrokeWidth(px1 * 5);
                mPaintGradientRim.setAlpha(100);
                gradientMatrix.preRotate((i + 1) * -6f, centerX, centerY);
                sweepGradient.setLocalMatrix(gradientMatrix);
                mPaintGradientRim.setShader(sweepGradient);
                canvasTemp.drawLine(centerX, centerY - bmTempHaftWidth + px1 * 25, centerX, centerY - bmTempHaftWidth + px1 * 15,
                        mPaintGradientRim);
                mPaintGradientRim.setAlpha(255);
            }

        }
    }
    private void drawTabGradient(Paint paint) {
        paint.setStrokeWidth(px1 * 15);
        canvasTemp.drawLine(centerX - px1 * 12, centerY + haftWidth / 1.5f, centerX + px1 * 12, centerY + haftWidth / 1.5f, paint);
        canvasTemp.drawLine(centerX - px1 * 12 + haftWidth / 1.5f, centerY, centerX + px1 * 12 + haftWidth / 1.5f, centerY, paint);
        canvasTemp.drawLine(centerX - px1 * 12 - haftWidth / 1.5f, centerY, centerX + px1 * 12 - haftWidth / 1.5f, centerY, paint);
        canvasTemp.drawPath(path, paint);
    }

}
