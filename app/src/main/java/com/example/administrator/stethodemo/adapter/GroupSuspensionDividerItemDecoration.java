package com.example.administrator.stethodemo.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.administrator.stethodemo.bean.CityEntity;
import com.example.administrator.stethodemo.utils.DisplayUtil;

import java.util.List;

/**
 * 作  者: roqy
 * 包  名: com.example.administrator.stethodemo
 * 日  期: 2018/7/18
 * 描  述:
 */

public class GroupSuspensionDividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private int mOverHeadHeight;//悬浮框高度
    private int mOverHeadPaddingLeft;//悬浮框文字显示距离左边距离
    List<CityEntity> mList;
    private String mPreGroupId;
    private String mConcurrentGroupId;
    private final Paint mTextPaint;


    public GroupSuspensionDividerItemDecoration(Context context, List<CityEntity> list) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#1C1D1E"));
        mList = list;
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(DisplayUtil.sp2px(context, 10));
        mTextPaint.setColor(Color.parseColor("#909399"));
        mOverHeadHeight = DisplayUtil.dip2px(context, 20);
        mOverHeadPaddingLeft = DisplayUtil.dip2px(context, 15);
    }

    /**
     * 分组悬框
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View childView = parent.getChildAt(i);
            int childAdapterPosition = parent.getChildAdapterPosition(childView);
            mConcurrentGroupId = mList.get(childAdapterPosition).getFirstWord();
            if (TextUtils.isEmpty(mPreGroupId)) {
                mPreGroupId = mList.get(childAdapterPosition).getFirstWord();
                c.drawRect(left, 0, right, mOverHeadHeight, mPaint);
            } else {
                mPreGroupId = mList.get(childAdapterPosition).getFirstWord();
                //绘制分组悬浮框
                c.drawRect(left, childView.getTop() - mOverHeadHeight, right, childView.getTop(), mPaint);
                c.drawText(mList.get(childAdapterPosition).getFirstWord(), mOverHeadPaddingLeft, childView.getTop() - mOverHeadHeight / 3, mTextPaint);
            }
        }
    }

    /**
     * 悬浮框
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        //始终取最上面一个
        View childView = parent.getChildAt(0);
        int childAdapterPosition = parent.getChildAdapterPosition(childView);

        //已经是当前分组中最后一个了
        if (!TextUtils.equals(mList.get(childAdapterPosition).getFirstWord(), mList.get(childAdapterPosition + 1).getFirstWord())) {
            //拿到最上面一个底部的距离
            int currentViewButton = childView.getBottom();
            //最上面的view的底部高度小于悬浮框的高度,开始联动
            if (currentViewButton <= mOverHeadHeight) {
                //上一个view的getButton小于0，说明已经完全消失,固定显示悬浮框
                if (currentViewButton <= 0) {
                    c.drawRect(left, 0, right, mOverHeadHeight, mPaint);
                    c.drawText(mList.get(childAdapterPosition + 1).getFirstWord(), mOverHeadPaddingLeft, mOverHeadHeight / 4 * 3, mTextPaint);
                } else {
                    c.drawRect(left, childView.getBottom() - mOverHeadHeight, right, childView.getBottom(), mPaint);
                    c.drawText(mList.get(childAdapterPosition).getFirstWord(), mOverHeadPaddingLeft, childView.getBottom() - mOverHeadHeight / 3, mTextPaint);
                }

            } else {
                c.drawRect(left, 0, right, mOverHeadHeight, mPaint);
                c.drawText(mList.get(childAdapterPosition).getFirstWord(), mOverHeadPaddingLeft, mOverHeadHeight / 4 * 3, mTextPaint);
            }

        } else {
            c.drawRect(left, 0, right, mOverHeadHeight, mPaint);
            c.drawText(mList.get(childAdapterPosition).getFirstWord(), mOverHeadPaddingLeft, mOverHeadHeight / 4 * 3, mTextPaint);
        }

    }

    /**
     * item预留空间
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, mOverHeadHeight, 0, 0);
        } else {
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (childAdapterPosition + 1 < mList.size()) {
                if (TextUtils.equals(mList.get(childAdapterPosition).getFirstWord(), mList.get(childAdapterPosition + 1).getFirstWord()))
                    outRect.set(0, 0, 0, 0);
                else
                    outRect.set(0, 0, 0, mOverHeadHeight);
            }

        }

    }
}
