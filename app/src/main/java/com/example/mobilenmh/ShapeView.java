package com.example.mobilenmh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ShapeView extends View {

    private int shapeColor = Color.BLACK;
    private int shapeType = 0; // 0: Circle, 1: Rectangle (Add more shapes as needed)

    private Paint paint;

    public ShapeView(Context context) {
        super(context);
        init();
    }

    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setShapeColor(int color) {
        this.shapeColor = color;
        invalidate(); // Request a redraw of the view
    }

    public void setShapeType(int type) {
        this.shapeType = type;
        invalidate(); // Request a redraw of the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY);

        paint.setColor(shapeColor);

        switch (shapeType) {
            case 0: // Circle
                canvas.drawCircle(centerX, centerY, radius, paint);
                break;
            case 1: // Rectangle
                float rectWidth = radius * 3;  // Set the width of the rectangle
                float rectHeight = radius * 1.5f;  // Set the height of the rectangle
                float left = centerX - rectWidth / 2;
                float top = centerY - rectHeight / 2;
                float right = centerX + rectWidth / 2;
                float bottom = centerY + rectHeight / 2;
                canvas.drawRect(left, top, right, bottom, paint);
                break;
            case 2: // Triangle
                float halfWidth = radius * (float) Math.sqrt(3) / 2;
                Path trianglePath = new Path();
                trianglePath.moveTo(centerX, centerY - radius);
                trianglePath.lineTo(centerX - halfWidth, centerY + radius / 2);
                trianglePath.lineTo(centerX + halfWidth, centerY + radius / 2);
                trianglePath.close();
                canvas.drawPath(trianglePath, paint);
                break;
            case 3: // Square
                canvas.drawRect(centerX - radius, centerY - radius, centerX + radius, centerY + radius, paint);
                break;
            case 4: // Oval
                RectF ovalRect = new RectF(centerX - radius, centerY - radius / 2, centerX + radius, centerY + radius / 2);
                canvas.drawOval(ovalRect, paint);
                break;
            // Add more cases for additional shape types
        }
    }
}

