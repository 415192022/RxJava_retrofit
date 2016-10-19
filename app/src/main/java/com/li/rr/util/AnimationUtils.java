package com.li.rr.util;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

/**
 * Created by LMW on 2016/8/31.
 * 属性动画配置
 */
public class AnimationUtils {
    private static AnimationUtils animationUtils;

    private AnimationUtils() {
    }

    public static AnimationUtils getInstance() {
        if (null == animationUtils) {
            synchronized (AnimationUtils.class) {
                if (null == animationUtils) {
                    animationUtils = new AnimationUtils();
                }
            }
        }
        return animationUtils;
    }

    /**
     * 创建一个抖动动画
     *
     * @param target
     * @param shakeFactor
     * @return
     */
    public void displayShakeAnimator(View target, float shakeFactor) {
        Keyframe scaleXkf0 = Keyframe.ofFloat(0f, 1f);
        Keyframe scaleXkf1 = Keyframe.ofFloat(0.1f, 0.9f);
        Keyframe scaleXkf2 = Keyframe.ofFloat(0.2f, 0.9f);
        Keyframe scaleXkf3 = Keyframe.ofFloat(0.3f, 0.9f);
        Keyframe scaleXkf4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleXkf5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleXkf6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleXkf7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleXkf8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleXkf9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXkf10 = Keyframe.ofFloat(1f, 1f);

        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofKeyframe("scaleX", scaleXkf0, scaleXkf1, scaleXkf2, scaleXkf3, scaleXkf4,
                scaleXkf5, scaleXkf6, scaleXkf7, scaleXkf8, scaleXkf9, scaleXkf10);

        Keyframe scaleYkf0 = Keyframe.ofFloat(0f, 1f);
        Keyframe scaleYkf1 = Keyframe.ofFloat(0.1f, 0.9f);
        Keyframe scaleYkf2 = Keyframe.ofFloat(0.2f, 0.9f);
        Keyframe scaleYkf3 = Keyframe.ofFloat(0.3f, 0.9f);
        Keyframe scaleYkf4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleYkf5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleYkf6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleYkf7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleYkf8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleYkf9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYkf10 = Keyframe.ofFloat(1f, 1f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofKeyframe("scaleY", scaleYkf0, scaleYkf1, scaleYkf2, scaleYkf3, scaleYkf4,
                scaleYkf5, scaleYkf6, scaleYkf7, scaleYkf8, scaleYkf9, scaleYkf10);


        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofKeyframe("rotation",
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(0.1f, -3 * shakeFactor),
                Keyframe.ofFloat(0.2f, -3 * shakeFactor),
                Keyframe.ofFloat(0.3f, 3 * shakeFactor),
                Keyframe.ofFloat(0.4f, -3 * shakeFactor),
                Keyframe.ofFloat(0.5f, 3 * shakeFactor),
                Keyframe.ofFloat(0.6f, -3 * shakeFactor),
                Keyframe.ofFloat(0.7f, -3 * shakeFactor),
                Keyframe.ofFloat(0.8f, 3 * shakeFactor),
                Keyframe.ofFloat(0.9f, -3 * shakeFactor),
                Keyframe.ofFloat(1f, 0));


        ObjectAnimator.ofPropertyValuesHolder(target, scaleXHolder, scaleYHolder, rotationHolder).setDuration(1000).start();
    }

    /**
     * 创建一个渐变动画
     *
     * @param view
     */
    public void displayAlpha(View view, long duration, float... fromTo) {
        ObjectAnimator alphaObjectAnimator = null;
        for (float f : fromTo) {
            alphaObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", f);
        }
        alphaObjectAnimator.setDuration(duration);
        alphaObjectAnimator.start();
    }

    /**
     * 创建一个旋转动画
     *
     * @param view
     * @param from
     * @param to
     * @param duration
     */
    public void displayRotate(View view, float from, float to, long duration) {
        ObjectAnimator rotationObjectAnimator = ObjectAnimator.ofFloat(view, "rotation", from, to);
        rotationObjectAnimator.setDuration(duration);
        rotationObjectAnimator.start();
    }

    /**
     * 自由落体重力弹性效果
     *
     * @param view
     * @param duration
     * @param from
     * @param to
     */
    public void dispalyTranslationY(View view, long duration, float from, float to) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", from, to);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    public void dispalyTranslation(View view, TranslationType translationType, long duration, float from, float to) {
        ObjectAnimator objectAnimator = null;
        if (translationType == TranslationType.translationX) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationX", from, to);
        }
        if (translationType == TranslationType.translationY) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", from, to);
        }
        if (null == objectAnimator) {
            return;
        }
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    public enum TranslationType {
        translationX, translationY
    }

}
