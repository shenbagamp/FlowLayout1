package com.example.shenbagampalanisamy.flowlayout1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import static android.content.ContentValues.TAG;
public class FlowLayout extends ViewGroup {
    Drawable d;
    int mHorizontalPadding;
    int mVerticalPadding;


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 200;
        int desiredHeight = 200;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        final View child=getChildAt(0);
        child.measure(
                getChildMeasureSpec(widthMeasureSpec, 0, child.getLayoutParams().width),
                getChildMeasureSpec(heightMeasureSpec, 0, child.getLayoutParams().height));

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {

            //Must be this size
            width = widthSize;
            Log.i("width1", String.valueOf(width));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
            Log.i("width2", String.valueOf(width));
        } else {
            //Be whatever you want
            width = desiredWidth;
            Log.i("width3", String.valueOf(width));
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
       // child.measure(4,5);

        //MUST CALL THIS
        Log.d(TAG, "setMeasuredDimension(" + width + ", " + height + ")");
        setMeasuredDimension(width, height);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // TODO Auto-generated method stub


        System.out.println("left_"+l+"top_"+t+"right_+"+r+"bott_"+b);

        final int count = getChildCount();
        Log.i("count", String.valueOf(count));
        int curWidth, curHeight, curLeft, curTop, maxHeight;

        //get the available size of child view
        int childLeft = this.getPaddingLeft();
        Log.i("childLeft", String.valueOf(childLeft));

        int childTop = this.getPaddingTop();

        int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        int childWidth = childRight - childLeft;
        int childHeight = childBottom - childTop;
        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        //walk through each child, and arrange it from left to right
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                //Get the maximum size of the child
                child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST),
                        MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
                curWidth = child.getMeasuredWidth();
                Log.i("cruw", String.valueOf(curWidth));
                System.out.println(curWidth);
                curHeight = child.getMeasuredHeight();
                //wrap is reach to the end
                if (curLeft + curWidth >= childRight) {
                    curLeft = childLeft;
                    curTop += maxHeight;
                    maxHeight = 0;
                    Log.i("curLeft", String.valueOf(curLeft));
                }
                //do the layout
                child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
                //store the max height
                System.out.println("max"+maxHeight);
                System.out.println("cur"+curHeight);
                if (maxHeight < curHeight)
                    maxHeight = curHeight;
               curLeft += curWidth;

            }
        }





    }

}
