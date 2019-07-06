package com.glens.jksd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.glens.jksd.R;

public class ProgressView extends View {
    private Context context;
    private String num;

    private float numTextsize;

    private int numTextColor;

    private float backCircleWidth;
    private float outerCircleWidth;

    private int backCircleColor;
    private int outerCircleColor;

    private Paint backCirclePaint,//画背景圆

    outerCirclePaint,//画进度圆弧

    numPaint;//画第二行文字


    private int width, height;
    private float currentPercent = 0.3f;

    private float edgeDistance;//背景圆与view边界的距离

    public ProgressView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
            num = array.getString(R.styleable.ProgressView_progress_num);
            numTextsize = array.getDimension(R.styleable.ProgressView_numTextSize, getResources().getDimension(R.dimen.px_to_dip_48));
            numTextColor = array.getColor(R.styleable.ProgressView_numTextColor, Color.parseColor("#4fc1e9"));
            backCircleWidth = array.getDimension(R.styleable.ProgressView_backCircleWidth, getResources().getDimension(R.dimen.px_to_dip_12));
            outerCircleWidth = array.getDimension(R.styleable.ProgressView_outerCircleWidth, getResources().getDimension(R.dimen.px_to_dip_20));
            backCircleColor = array.getColor(R.styleable.ProgressView_backCircleColor, Color.parseColor("#e6e9ed"));
            outerCircleColor = array.getColor(R.styleable.ProgressView_outerCircleColor, Color.parseColor("#4fc1e9"));
            edgeDistance = array.getDimension(R.styleable.ProgressView_edgeDistance, getResources().getDimension(R.dimen.px_to_dip_12));
            backCirclePaint = new Paint();
            backCirclePaint.setAntiAlias(true);
            backCirclePaint.setStrokeWidth(backCircleWidth);
            backCirclePaint.setColor(backCircleColor);
            backCirclePaint.setStyle(Paint.Style.STROKE);
            outerCirclePaint = new Paint();
            outerCirclePaint.setAntiAlias(true);
            outerCirclePaint.setStrokeWidth(outerCircleWidth);
            outerCirclePaint.setColor(outerCircleColor);
            outerCirclePaint.setStyle(Paint.Style.STROKE);
            outerCirclePaint.setStrokeCap(Paint.Cap.ROUND);

            numPaint = new Paint();

            numPaint.setAntiAlias(true);

            numPaint.setColor(numTextColor);

            numPaint.setTextSize(numTextsize);

        }

    }


    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        width = MeasureSpec.getSize(widthMeasureSpec);

        height = MeasureSpec.getSize(heightMeasureSpec);


        if (width > height) {

            width = height;

        }


        if (height > width) {

            height = width;

        }


        setMeasuredDimension(width, height);

    }


    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        int centerX = width / 2;

        int centerY = height / 2;


        //计算半径

        float radius = (width / 2) - edgeDistance;


        //半径是圆心与画出来的圆环中间点连起来的位置

        //画背景圆

        canvas.drawCircle(centerX, centerY, radius, backCirclePaint);


        //根据进度话扫过一定角度的圆弧

        RectF rectF = new RectF(edgeDistance, edgeDistance, width - edgeDistance, height - edgeDistance);

        canvas.drawArc(rectF, -90, 360 * currentPercent, false, outerCirclePaint);


        //画文字

        Rect textRect = new Rect();

        numPaint.getTextBounds(num, 0, num.length(), textRect);

        canvas.drawText(num, width / 2 - textRect.width() / 2, height / 2 + textRect.height() / 2, numPaint);


        //我这里规定进度在0~100%的时候才会画终点小圆，可以自由改动

//        if (currentPercent < 1 && currentPercent > 0) {
//
//            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
//
//                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), endCircleWidth / 2, endCirclePaint);
//
//
//            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
//
//                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), endCircleWidth / 4, endCirclePaint2);
//
//
//        }

    }


    public void setValue(String value) {

        this.num = value;

        invalidate();

    }


    public void setCurrentPercent(float currentPercent) {

        this.currentPercent = currentPercent;

        invalidate();

    }

}
