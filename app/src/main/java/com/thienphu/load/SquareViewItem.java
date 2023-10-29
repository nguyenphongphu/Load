package com.thienphu.load;

import android.content.Context;

import android.util.AttributeSet;

;

public class SquareViewItem extends androidx.appcompat.widget.AppCompatImageView {

    public SquareViewItem(Context context) {
        super(context);
    }

    public SquareViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}