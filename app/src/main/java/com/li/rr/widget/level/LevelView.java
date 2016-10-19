package com.li.rr.widget.level;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/5.
 */
public class LevelView extends LinearLayout {
    private int diamondsCount;
    private int crownCount;
    private int diamondsDrawble;
    private int crownDrawble;
    private Context context;
    private int hex;
    private int level;

    public LevelView(Context context) {
        super(context);
        init(context);
    }

    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight = 0;
        int maxWidth = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            maxWidth += child.getMeasuredWidth();
            maxHeight = child.getMeasuredHeight();
            // 测量该子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        //报告我们最终计算出的宽高。
        setMeasuredDimension(maxWidth, maxHeight);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View view = getChildAt(i);
//            view.layout(i * view.getWidth(), l, view.getWidth() * i + view.getWidth(), b);
//        }
//    }

    private void init(Context context) {
        this.context = context;
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * 2
     *
     * @param hex
     */
    public LevelView setHex(int hex) {
        this.hex = hex;
        return this;
    }

    /**
     * 4
     *
     * @param diamondsDrawble
     */
    public LevelView setdiamondsDrawble(int diamondsDrawble) {
        this.diamondsDrawble = diamondsDrawble;
        return this;
    }

    /**
     * 4
     *
     * @param crownDrawble
     */
    public LevelView setcrownDrawble(int crownDrawble) {
        this.crownDrawble = crownDrawble;
        invalidate();
        return this;
    }

    /**
     * 所有属性都设置好了提示更新UI
     */
    public void makeLevel() {
        calculationCD();
        makeLevelDrawble(diamondsCount, crownCount, diamondsDrawble, this.crownDrawble);
    }

    /**
     * 1
     *
     * @param level
     */
    public LevelView setLevel(int level) {
        this.level = level;
        return this;
    }

    /**
     * 3
     */
    public void calculationCD() {
        crownCount = level / hex;
        diamondsCount = level % hex;
        if (crownCount > 5 && level == 30) {
            crownCount = crownCount - 1;
            diamondsCount = 5;
        }
    }

    private void makeLevelDrawble(int diamondsCount, int crownCount, int diamondsDrawble, int crownDrawble) {
        for (int i = 0; i < crownCount; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(crownDrawble);
//            imageView.setBackgroundColor(Color.parseColor("#F88765"));
            this.addView(imageView);
        }
        for (int i = 0; i < diamondsCount; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(diamondsDrawble);
//            imageView.setBackgroundColor(Color.parseColor("#F65153"));
            addView(imageView);
        }
        invalidate();
    }
}
