package com.example.dongwei.airtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by dongwei on 2016/12/21.
 */

public class MyScrollView extends ScrollView {

    private OnScrollListener onScrollListener;


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }

    @Override
    public int computeVerticalScrollRange(){
        return super.computeVerticalScrollRange();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null){
            onScrollListener.onScroll(t);
        }
    }

    public interface OnScrollListener{

        public void onScroll(int scrollY);
    }
}
