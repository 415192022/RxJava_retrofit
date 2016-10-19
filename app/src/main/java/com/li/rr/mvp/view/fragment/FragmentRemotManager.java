package com.li.rr.mvp.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.li.rr.base.fragment.BaseFragmentV4;
import com.li.rr.util.AnimationUtils;
import com.li.rr.widget.level.LevelView;
import com.li.rr.widget.level.StaggerLayout;
import com.li.rr.widget.marquee.MarqueeView;

import java.util.Random;

import rr.li.com.rxjavaretrofit.R;

/**
 * Created by Administrator on 2016/5/24.
 */
public class FragmentRemotManager extends BaseFragmentV4 {

    Button btn_switch;
    LevelView lev_levelView;
    MarqueeView mv_marqueeview;

    @Override
    public int bindLayout() {
        return R.layout.fragment_4;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        thisView=view;
        btn_switch= (Button) view.findViewById(R.id.btn_switch);
        lev_levelView= (LevelView) view.findViewById(R.id.lev_levelView);
        //设置进制
        lev_levelView.setHex(5)
        //设置等级
        .setLevel(1)
        //设置钻石图片
        .setdiamondsDrawble(R.drawable.spxq_zuan)
        //设置皇冠
        .setcrownDrawble(R.drawable.spxq_huangguan)
        //最后调用
        .makeLevel();
        mv_marqueeview= (MarqueeView) view.findViewById(R.id.mv_marqueeview);
        mv_marqueeview
                .setLoop(true)
                .setMarqueeViewBackgroundColor(Color.WHITE)
                .setSpeed(0)
                .setTextColor(Color.BLUE)
                .setTextSize(15)
                .setTextWidthHeight(13,13)
                .setOritation(MarqueeView.Scoll.TO_RIGHT)
                .setStatEndPosition(MarqueeView.StartEndPosition.LEFT_TO_RIGHT)
                .setText("我是王少剑")
                .startScroll();
    }
    View thisView=null;
    @Override
    public void doBusiness(Context mContext) {

        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != thisView){
                    AnimationUtils.getInstance().dispalyTranslation(v, AnimationUtils.TranslationType.translationY,800,0,300);
                }
            }
        });
    }

    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }
    /**
     * 展示一个切换动画
     */
    private void showAnimation(Context context) {
        final View decorView = ((AppCompatActivity)context).getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(getActivity());
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotate", 0, 360);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }



}



