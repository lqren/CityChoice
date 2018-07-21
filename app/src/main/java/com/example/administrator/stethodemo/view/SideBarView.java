package com.example.administrator.stethodemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.stethodemo.utils.DisplayUtil;

/**
 * 作  者: roqy
 * 包  名: com.example.administrator.stethodemo.view
 * 日  期: 2018/7/20
 * 描  述:
 */

public class SideBarView extends View {
    //要绘制的文字
    private String mLettersShow[] = {"★", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private Paint mTextPaint;
    private float textMarginBottom;//文字间行间距
    private int mLetterHeight;
    private int mTextSize;
    private int mWidthSize;
    private int lastIndex = -1;//标记上次的触摸字母的索引

    public SideBarView(Context context) {
        this(context, null);
    }

    public SideBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#ffffff"));
        mTextSize = DisplayUtil.sp2px(context, 10);
        mTextPaint.setTextSize(mTextSize);
        //文字居中
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        textMarginBottom = DisplayUtil.px2dip(context, 28);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //单个文字显示高度
        mLetterHeight = (int) (mTextSize + textMarginBottom);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {

        }
    }

    /**
     * 设置索引数组，默认是26个字母
     * @param list
     */
    public void setIndexDatas(String[] list){
        mLettersShow = list;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制每一个字母
        for (int i = 0; i < mLettersShow.length; i++) {
            canvas.drawText(mLettersShow[i], mWidthSize / 2, mLetterHeight * (i + 1), mTextPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float touchY = event.getY();
                int index = (int) (touchY / mLetterHeight);//得到当前索引
                if (lastIndex != index) {//判断上次点击的索引是否跟本次相同
                    if (index >= 0 && index < mLettersShow.length) {
                        //判断触摸的范围是否在所有字母的高度之内
                        if (mOnTouchListeber != null) {
                            mOnTouchListeber.onTouch(mLettersShow,index);
                        }
                    }
                }
                lastIndex = index;
                break;
            case MotionEvent.ACTION_UP:
                //滑动触目事件消失，标记的位置索引就要进行重置
                mOnTouchListeber.dismiss();
                lastIndex = -1;
                break;
            default:
                break;
        }

        return true;
    }

    private OnTouchListeber mOnTouchListeber;

    public void setOnTouchListeber(OnTouchListeber listeber) {
        mOnTouchListeber = listeber;
    }

    public interface OnTouchListeber {
        void onTouch(String[] mLettersShow, int pos);

        void dismiss();
    }
}
