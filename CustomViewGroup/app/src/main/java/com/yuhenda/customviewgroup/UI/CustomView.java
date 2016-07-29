package com.yuhenda.customviewgroup.UI;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuhenda.customviewgroup.MainActivity;
import com.yuhenda.customviewgroup.R;

/**
 * Created by ljzyuhenda on 16/7/22.
 */
public class CustomView extends ViewGroup {
    /**
     * 终点坐标    返回point值/colum值
     * x轴  -> 余数不为0,则是余数值
     * 余数为0,则是colum值
     * y轴  -> 余数不为0,则是商+1
     * 余数为0,则是商值
     * 起点坐标
     * x轴  终点坐标 - 1
     * y轴  终点坐标 - 1
     */
    private int mColum = 3;
    //    private String[] mDatas = {"1,2", "3,6", "4", "5"};
    private String[] mDatas = {"1,2,3", "4,5", "6"};
    private int[] mColors = {R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5};
    private int mItemHeight = 400;
    private int mRow = 2;
    private int mMaxPoint = mColum * mRow;
    private String[] mChildrenInfos;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    /**
     * left:在父布局的左侧开始位置
     * top:在父布局的上部开始位置
     */
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        if (getChildCount() > 0) {
            // 那么遍历子元素并对其进行定位布局
            for (int index = 0; index < getChildCount(); index++) {
                View child = getChildAt(index);
                String[] childInfos = mChildrenInfos[index].split(",");
                child.layout(Integer.valueOf(childInfos[0]), Integer.valueOf(childInfos[1]), Integer.valueOf(childInfos[2]), Integer.valueOf(childInfos[3]));
            }
        }
    }

    public void setDatas(String[] datas) {
        if (datas != null) {
            mDatas = datas;
        }


        mChildrenInfos = new String[mDatas.length];
        int itemHeight = 600;
        int itemWidth = MainActivity.getScreenWidth((Activity) getContext());

        for (int index = 0; index < mDatas.length; index++) {
            String dateEve = mDatas[index];

            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view, null);
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.act_live_hot_item, null);
            view.setBackgroundResource(mColors[index]);
            String[] pointArr = dateEve.split(",");
            int childLeft;
            int childTop;
            int childRight;
            int childBottom;
            if (pointArr.length == 1) {
                childLeft = getOriginalX(pointArr[0]) * itemWidth / mColum;
                childTop = getOriginalY(pointArr[0]) * itemHeight / mRow;
                childRight = getFinalPointX(pointArr[0]) * itemWidth / mColum;
                childBottom = getFinalPointY(pointArr[0]) * itemHeight / mRow;

            } else {
                int size = pointArr.length;
                childLeft = getOriginalX(pointArr[0]) * itemWidth / mColum;
                childTop = getOriginalY(pointArr[0]) * itemHeight / mRow;
                childRight = getFinalPointX(pointArr[size - 1]) * itemWidth / mColum;
                childBottom = getFinalPointY(pointArr[size - 1]) * itemHeight / mRow;
            }

            mChildrenInfos[index] = childLeft + "," + childTop + "," + childRight + "," + childBottom;
            LayoutParams params = new LayoutParams(childRight - childLeft, childBottom - childTop);
            addView(view, params);
        }

//        invalidate();
    }

    private int getFinalPointX(String point) {
        int iData = Integer.valueOf(point) % mColum;

        if (iData == 0) {
            return mColum;
        } else {
            return iData;
        }
    }

    private int getFinalPointY(String point) {
        int iData = Integer.valueOf(point) % mColum;//余数
        int iData2 = Integer.valueOf(point) / mColum;//商

        if (iData == 0) {
            return iData2;
        } else {
            return iData2 + 1;
        }
    }

    private int getOriginalX(String point) {
        return getFinalPointX(point) - 1;
    }

    private int getOriginalY(String point) {
        return getFinalPointY(point) - 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }

//        /**
//         * widthMeasureSpec,heightMeasureSpec 根据父状态和自己自身状态判断的值
//         */
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int myWidth = -1;
//        int myHeight = -1;
//
//        //最终测量大小
//        int width = 0;
//        int height = 0;
//        //不是随意大小,则最大是算好的值
//        if (widthMode != MeasureSpec.UNSPECIFIED) {
//            myWidth = widthSize;
//        }
//
//        if (heightMode != MeasureSpec.UNSPECIFIED) {
//            myHeight = heightSize;
//        }
//
//        //如果是规定好的尺寸,则直接取算好的值
//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = myWidth;
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = myHeight;
//        }
//
//
//
//        setMeasuredDimension(width, height);
    }
}
