package com.androidatc.customviewindrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by seymourdiera on 7/27/2015.
 */
public class AndroidATCView extends View {

    // Square and text colors
    private int squareCol, labelCol;
    // Label text
    private String squareText;
    // Paint for drawing custom view
    // The Paint class holds the style and color information about how to draw geometries, text and bitmaps.
    private Paint squarePaint;

    public AndroidATCView(Context context, AttributeSet attrs) {
        super(context, attrs);

        squarePaint = new Paint();

        // Return a TypedArray holding the attribute values in set that are listed in attrs
        TypedArray a = context.getTheme().obtainStyledAttributes(
                // Any attribute values in the given AttributeSet.
                attrs,
                // The style resource specified in the AttributeSet
                R.styleable.AndroidATCView,
                // he default style specified by defStyleAttr and defStyleRes
                0,
                // The base values in this theme.
                0);
        try{
            // get the text and colors secified using the names in attrs.xml
            squareText = a.getString(R.styleable.AndroidATCView_squareLabel);
            squareCol = a.getInteger(R.styleable.AndroidATCView_squareColor, 0); // 0 is default
            labelCol = a.getInteger(R.styleable.AndroidATCView_labelColor, 0);
        }
        finally {
            // Be sure to call TypedArray.recycle() when you are done with the array.
            a.recycle();
        }
    }

    public int getSquareCol() {
        return squareCol;
    }

    public void setSquareCol(int squareCol) {
        // Update the instance variable
        this.squareCol = squareCol;
        // redraw the view
        // Invalidate the whole view.
        // If the view is visible, onDraw(android.graphics.Canvas) will be called at some point in the future.
        invalidate();
        // Call this when something has changed which has invalidated the layout of this view.
        // This will schedule a layout pass of the view tree.
        requestLayout();
    }

    public int getLabelCol() {
        return labelCol;
    }

    public void setLabelCol(int labelCol) {
        this.labelCol = labelCol;
        invalidate();
        requestLayout();
    }

    public String getSquareText() {
        return squareText;
    }

    public void setSquareText(String squareText) {
        this.squareText = squareText;
        invalidate();
        requestLayout();
    }

    /**
     * The implementation of this method will draw the view and specify its visual properties.
     * @param canvas The Canvas class holds the "draw" calls.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // Set the deawing properties
        // The Style specifies if the primitive being drawn is filled, stroked, or both (in the same color). The default is FILL.
        // Geometry and text drawn with this style will be filled, ignoring all stroke-related settings in the paint.
        squarePaint.setStyle(Paint.Style.FILL);
        squarePaint.setAntiAlias(true);

        // set the paint color using the square color specified
        squarePaint.setColor(squareCol);

        // Draw the square using the properties defined
        // Draw the specified Rect using the specified paint. The rectangle will be filled or framed based on the Style in the paint.
        canvas.drawRect(
                // The left side of the rectangle to be drawn
                0,
                // The top side of the rectangle to be drawn
                0,
                // Right side
                //The raw measured width of this view
                this.getMeasuredWidth(),
                // Bottom
                // The raw measured height of this view
                this.getMeasuredHeight(),
                // The paint used to draw the rect
                squarePaint);

        // Set the text color using the color specified
        squarePaint.setColor(labelCol);

        // Set the properties
        squarePaint.setTextAlign(Paint.Align.CENTER);
        squarePaint.setTextSize(50);

        // Draw the text using the string attribute and chosen properties
//        Draw the text, with origin at (x,y), using the specified paint
        canvas.drawText(
                // The text to be drawn
                squareText,
                // The x-coordinate of the origin of the text being drawn
                this.getMeasuredWidth()/2,
                // The y-coordinate of the origin of the text being drawn
                this.getMeasuredHeight()/2,
                // The paint used for the text
                squarePaint);








        super.onDraw(canvas);
    }
}
