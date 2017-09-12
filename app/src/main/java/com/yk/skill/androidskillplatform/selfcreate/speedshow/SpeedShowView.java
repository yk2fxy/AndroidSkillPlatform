package com.yk.skill.androidskillplatform.selfcreate.speedshow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yk.skill.androidskillplatform.R;

import java.math.BigDecimal;

/**
 * TODO: document your custom view class.
 */
public class SpeedShowView extends View {
    private final static String TAG = SpeedShowView.class.getSimpleName();
    int viewHeight, viewWidth;
    int circleHeight, circleWidth;
    int startAnglee, endAnglee;
    int maxSpeed, minSpeedUnit;
    int speed;
    boolean baseCompleted;
    int pointColor;
    int circleColor, circleBordor;
    int centerColor,centerRadius;


    public SpeedShowView(Context context) {
        super(context);
        init(null, 0, context);
    }

    public SpeedShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, context);
    }

    public SpeedShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle, context);
    }

    private void init(AttributeSet attrs, int defStyle, Context context) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpeedShowView);
        startAnglee = ta.getInteger(R.styleable.SpeedShowView_startAnglee, 0);
        endAnglee = ta.getInteger(R.styleable.SpeedShowView_endAnglee, 0);
        if (startAnglee >= 270 && startAnglee < 360) {
            startAnglee = startAnglee - 90;
        } else if (startAnglee > 0 && startAnglee < 90) {
            startAnglee = startAnglee + 90;
        }
        endAnglee = 360 - (startAnglee - 90) * 2;
        maxSpeed = ta.getInteger(R.styleable.SpeedShowView_maxSpeed, 120);
        minSpeedUnit = ta.getInteger(R.styleable.SpeedShowView_minSpeedUnit, 10);
        pointColor = ta.getColor(R.styleable.SpeedShowView_pointColor,0x00ffaaaa);
        circleColor = ta.getColor(R.styleable.SpeedShowView_circleColor,Color.BLACK);
        circleBordor = ta.getInteger(R.styleable.SpeedShowView_circleBordor,10);

        centerColor = ta.getColor(R.styleable.SpeedShowView_centerColor,Color.BLACK);
        centerRadius = ta.getInteger(R.styleable.SpeedShowView_centerRadius,20);
        invalidate();
        initViewData();
    }

    private void initViewData() {
        baseCompleted = false;
        speed = 0;
        circleHeight = 0;
        circleWidth = 0;
        viewHeight = 0;
        viewWidth = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                setBackgroundColor(Color.RED);
                break;
            case MeasureSpec.EXACTLY:
                circleHeight = height;
                circleWidth = width;
                setBackgroundColor(Color.WHITE);
                break;
            case MeasureSpec.UNSPECIFIED:
                setBackgroundColor(Color.BLACK);
                break;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBaseView(canvas);
        canvas.save();
        drawDynLines(canvas);
    }

    private void drawDynLines(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(pointColor);
        paint.setAntiAlias(false);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        int xPos = circleWidth > circleHeight ? (circleWidth - circleHeight) / 2 : circleBordor;
        int yPos = circleHeight > circleWidth ? (circleHeight - circleWidth) / 2 : circleBordor;
        float speedAnglee ;
        BigDecimal bd1 = new BigDecimal(speed);
        BigDecimal bd2 = new BigDecimal(endAnglee);
        BigDecimal bd3 = new BigDecimal(maxSpeed);
        speedAnglee = bd1.multiply(bd2).divide(bd3,2,BigDecimal.ROUND_HALF_UP).floatValue();
        double d = Math.toDegrees(speedAnglee);
        Log.i(TAG, "onDraw: d=" + d + "--->" + speedAnglee + "--->" + speed +"--->anglee="+bd1.multiply(bd2).divide(bd3,2,BigDecimal.ROUND_HALF_UP));
        canvas.rotate(speedAnglee, circleWidth / 2, circleHeight / 2);
        canvas.drawLine(xPos + viewWidth / 2, viewHeight / 2 + yPos + circleBordor, xPos + viewWidth / 2, viewHeight / 20 + yPos, paint);
    }

    private void drawBaseView(Canvas canvas) {
        Paint paint = new Paint();
        int xPos = circleWidth > circleHeight ? (circleWidth - circleHeight) / 2 : circleBordor;
        int yPos = circleHeight > circleWidth ? (circleHeight - circleWidth) / 2 : circleBordor;
        viewWidth = getMin(circleHeight, circleWidth) - circleBordor;
        viewHeight = viewWidth;
        paint.setAntiAlias(false);

        drawCenterCircle(canvas, paint, xPos, yPos);

        drawBaseArc(canvas, paint, xPos, yPos);

        drawRateLine(canvas, paint, xPos, yPos);

    }

    private void drawRateLine(Canvas canvas, Paint paint, int xPos, int yPos) {
        int count = maxSpeed / minSpeedUnit;
        canvas.rotate(90 + startAnglee, circleWidth / 2, circleHeight / 2);
        for (int i = 0; i < count + 1; i++) {
            paint.setTextSize(60);
            if (i % 2 == 0) {
                paint.setColor(Color.RED);
                paint.setStrokeWidth(20);
                canvas.drawLine(xPos + viewWidth / 2, yPos, xPos + viewWidth / 2, viewHeight / 20 + yPos, paint);
                paint.setStrokeWidth(10);
                canvas.drawText("" + i, xPos + viewWidth / 2 - 25, yPos + 100, paint);
            } else {
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(10);
                canvas.drawLine(xPos + viewWidth / 2, yPos, xPos + viewWidth / 2, viewHeight / 10 + yPos, paint);
                canvas.drawText("" + i, xPos + viewWidth / 2 - 25, yPos + 150, paint);
            }
            BigDecimal bd1 = new BigDecimal(endAnglee);
            BigDecimal bd2 = new BigDecimal(count);

            float f = bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP).floatValue();
            if (i != count) canvas.rotate(f, circleWidth / 2, circleHeight / 2);
        }
        canvas.rotate((startAnglee - 90) * 2, circleWidth / 2, circleHeight / 2);
    }

    private void drawBaseArc(Canvas canvas, Paint paint, int xPos, int yPos) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleColor);
        canvas.drawArc(xPos, yPos, viewWidth + xPos, viewHeight + yPos, startAnglee, endAnglee, false, paint);
    }

    private void drawCenterCircle(Canvas canvas, Paint paint, int xPos, int yPos) {
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(centerColor);
        canvas.drawCircle(xPos + viewWidth / 2, viewHeight / 2 + yPos, centerRadius, paint);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        invalidate();
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private int getMin(int circleHeight, int circleWidth) {
        return circleHeight > circleWidth ? circleWidth : circleHeight;
    }
}
