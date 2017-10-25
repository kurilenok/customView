package org.numisoft.customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by kurilenok on 10/25/17.
 */

public class CustomView extends FrameLayout {

    private int dotSelected = -1;
    private Paint paint;
    private float radius;
    private float stroke;

    private int width;
    private int height;


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            dotSelected = a.getInt(R.styleable.CustomView_dot_selected, 0);
            radius = a.getDimension(R.styleable.CustomView_radius, 0.0f);
            stroke = a.getDimension(R.styleable.CustomView_stroke, 0.0f);
        } finally {
            a.recycle();
        }

        paint = new Paint();
        paint.setColor(Color.BLACK);
        if (dotSelected > 0) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(stroke);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = canvas.getWidth();
        height = canvas.getHeight();

        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }

    public void setDotSelected(int dotSelected) {
        this.dotSelected = dotSelected;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        invalidate();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) radius * 2 + getPaddingLeft() + getPaddingRight();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) radius * 2 + getPaddingTop() + getPaddingBottom();
    }




}
