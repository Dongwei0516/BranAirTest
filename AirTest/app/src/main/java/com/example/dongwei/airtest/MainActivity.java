package com.example.dongwei.airtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements MyScrollView.OnScrollListener {

    private MyScrollView myScrollView;
    private LinearLayout mLayout;
    private LinearLayout mTopLayout;
    private TextView mTime1, mTime2, mTime3, mTime4, mTime5;
    private TextView mPM,mtVOC,mCO2e,mTem,mHum;
    private TextView mNum1, mNum2, mNum3,mNum4,mNum5;
    private TextView mLevel1, mLevel2,mLevel3,mLevel4,mLevel5;
    private RelativeLayout mTable1, mTable2,mTable3,mTable4,mTable5;
    private boolean isAnimation = false;
    private float mRate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        myScrollView = (MyScrollView)findViewById(R.id.myScrollView);
        mLayout = (LinearLayout)findViewById(R.id.airBar);
        mTopLayout = (LinearLayout)findViewById(R.id.topBar);
//        mLinearLayout = (LinearLayout)findViewById(R.id.Linear);

        mTime1 = (TextView)mTopLayout.findViewById(R.id.time1) ;
        mTime2 = (TextView)mTopLayout.findViewById(R.id.time2) ;
        mTime3 = (TextView)mTopLayout.findViewById(R.id.time3) ;
        mTime4 = (TextView)mTopLayout.findViewById(R.id.time4) ;
        mTime5 = (TextView)mTopLayout.findViewById(R.id.time5) ;

        mPM = (TextView)mTopLayout.findViewById(R.id.name1);
        mtVOC = (TextView)mTopLayout.findViewById(R.id.name2);
        mCO2e = (TextView)mTopLayout.findViewById(R.id.name3);
        mTem = (TextView)mTopLayout.findViewById(R.id.name4);
        mHum = (TextView)mTopLayout.findViewById(R.id.name5);

        mNum1 = (TextView)mTopLayout.findViewById(R.id.num1);
        mNum2 = (TextView)mTopLayout.findViewById(R.id.num2);
        mNum3 = (TextView)mTopLayout.findViewById(R.id.num3);
        mNum4 = (TextView)mTopLayout.findViewById(R.id.num4);
        mNum5 = (TextView)mTopLayout.findViewById(R.id.num5);

        mLevel1 = (TextView)mTopLayout.findViewById(R.id.level1);
        mLevel2 = (TextView)mTopLayout.findViewById(R.id.level2);
        mLevel3 = (TextView)mTopLayout.findViewById(R.id.level3);
        mLevel4 = (TextView)mTopLayout.findViewById(R.id.level4);
        mLevel5 = (TextView)mTopLayout.findViewById(R.id.level5);

        mTable1 = (RelativeLayout)mTopLayout.findViewById(R.id.table1);
        mTable2 = (RelativeLayout)mTopLayout.findViewById(R.id.table2);
        mTable3 = (RelativeLayout)mTopLayout.findViewById(R.id.table3);
        mTable4 = (RelativeLayout)mTopLayout.findViewById(R.id.table4);
        mTable5 = (RelativeLayout)mTopLayout.findViewById(R.id.table5);

        mTopLayout.setVisibility(View.VISIBLE);
        mLayout.setVisibility(View.INVISIBLE);

        myScrollView.setOnScrollListener(this);

        findViewById(R.id.activity_main).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(myScrollView.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {

        int mLayoutTop = Math.max(scrollY,mLayout.getTop());
        int mLayoutBottom = Math.max(mLayoutTop+mLayout.getHeight()-myScrollView.getScrollY()+mLayout.getTop(),mLayoutTop+mLayout.getHeight()/2);
        int mTableTop = Math.max(mTable1.getScrollY(),mTable1.getTop());
//        int mTableTop2 = Math.max(mTable2.getScrollY(),mTable2.getTop());
//        int mTableTop3 = Math.max(mTable2.getScrollY(),mTable3.getTop());
//        int mTableTop4 = Math.max(mTable2.getScrollY(),mTable4.getTop());
//        int mTableTop5 = Math.max(mTable2.getScrollY(),mTable5.getTop());

        int mTableBottom = Math.max(184-mLayoutTop+mLayout.getTop(),mTableTop+92);
        int mTableBottom2 = Math.max(184*2-mLayoutTop+mLayout.getTop(), mTableTop+92*2);
        int mTableBottom3 = Math.max(184*3-mLayoutTop+mLayout.getTop(), mTableTop+92*3);
        int mTableBottom4 = Math.max(184*4-mLayoutTop+mLayout.getTop(), mTableTop+92*4);
        int mTableBottom5 = Math.max(184*5-mLayoutTop+mLayout.getTop(), mTableTop+92*5);

        mRate = (float) ((scrollY-mLayout.getTop())*1.0/92);
        Log.d("Rate", String.valueOf(mRate));

        if (mRate>=1){
            mTime1.setVisibility(View.GONE);
            mTime2.setVisibility(View.GONE);
            mTime3.setVisibility(View.GONE);
            mTime4.setVisibility(View.GONE);
            mTime5.setVisibility(View.GONE);
        }else {
            mTime1.setVisibility(View.VISIBLE);
            mTime1.setAlpha(1-mRate);
            mTime2.setVisibility(View.VISIBLE);
            mTime2.setAlpha(1-mRate);
            mTime3.setVisibility(View.VISIBLE);
            mTime3.setAlpha(1-mRate);
            mTime4.setVisibility(View.VISIBLE);
            mTime4.setAlpha(1-mRate);
            mTime5.setVisibility(View.VISIBLE);
            mTime5.setAlpha(1-mRate);
        }

        Log.d("444", String.valueOf(mLevel1.getTextSize()));
        int size1 = (int) mNum1.getTextSize();
        float mTextMax = (float) Math.max(0.5, 1 - mRate);
        if (mRate>=0) {
            mNum1.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (131.0 * mTextMax));
            mNum2.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (131.0 * mTextMax));
            mNum3.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (131.0 * mTextMax));
            mNum4.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (131.0 * mTextMax));
            mNum5.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (131.0 * mTextMax));

            mPM.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float)(79.0 * mTextMax));
            mtVOC.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float)(79.0 * mTextMax));
            mCO2e.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float)(79.0 * mTextMax));
            mTem.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float)(79.0 * mTextMax));
            mHum.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float)(79.0 * mTextMax));

            mLevel1.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (53.0*mTextMax));
            mLevel2.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (53.0*mTextMax));
            mLevel3.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (53.0*mTextMax));
            mLevel4.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (53.0*mTextMax));
            mLevel5.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (53.0*mTextMax));
        }

          mTopLayout.layout(0,mLayoutTop,mTopLayout.getWidth(),mLayoutBottom);


        mTable1.layout(0,mTableTop,mLayout.getWidth(),mTableBottom);
        mTable2.layout(0,mTableBottom,mLayout.getWidth(),mTableBottom2);
        mTable3.layout(0,mTableBottom2,mLayout.getWidth(),mTableBottom3);
        mTable4.layout(0,mTableBottom3,mLayout.getWidth(),mTableBottom4);
        mTable5.layout(0,mTableBottom4,mLayout.getWidth(),mTableBottom5);

    }


}

