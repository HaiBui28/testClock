package com.tapbi.spark.testzip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomBackground extends View {
    RectF rectF;
    Paint paint;
    Rect mRect = new Rect();
    Paint getPaintBackgroung = new Paint();
    Paint paint2 = new Paint();
    Paint paintLine = new Paint();
    Canvas canvas;

    public CustomBackground(Context context) {
        super(context);
        initData();
    }

    public CustomBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public CustomBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        paint = new Paint();
        paint.setTextSize(dpToPixels(30));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.YELLOW);

        paint.setStrokeCap(Paint.Cap.ROUND);
        getPaintBackgroung.setColor(Color.RED);
        paint2.setColor(Color.BLACK);
        paintLine.setStrokeCap(Paint.Cap.ROUND);
        paintLine.setColor(Color.RED);
        paintLine.setStrokeWidth(10);
        paintLine.setAntiAlias(true);
//        setBackgroundColor(Color.RED);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
//        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private void draw() {

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
//        rectF = new RectF(0,0,getWidth(),getHeight());
//        canvas.drawRoundRect(rectF,20,20,getPaintBackgroung);
        paint.setXfermode(null);

   //     canvas.drawBitmap(drawableToBitmap(getContext().getDrawable(R.drawable.rectangle_4699)), 0, 0, paint);
        //
        canvas.save();
        float rectWidth = getWidth() / 2f;
        float rectHeight = getHeight() / 2f;
        float rectLeft = (getWidth() - rectWidth) / 2;
        float rectTop = (getHeight() - rectHeight) / 2;
        float rectRight = rectLeft + rectWidth;
        float rectBottom = rectTop + rectHeight;
        RectF rectF = new RectF(rectLeft, rectTop, rectRight, rectBottom);
        paint.setXfermode(null);
        canvas.drawRoundRect(rectF, 20, 20, paint);
        //

        paint.getTextBounds(String.valueOf(10), 0, String.valueOf(10).length(), mRect);

        String text = "10";
        float textWidth = paint.measureText(text);
        float textHeight = paint.descent() - paint.ascent();
        float x = (getWidth() - textWidth) / 2;
        float y = (getHeight() - textHeight) / 2 - paint.ascent();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawText(text, x, y, paint);
        canvas.drawLine((getWidth() - rectWidth) / 2,getHeight()/2f,(getWidth() - rectWidth/2),getHeight()/2f,paint);
        canvas.restore();
    }

    public int dpToPixels(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
