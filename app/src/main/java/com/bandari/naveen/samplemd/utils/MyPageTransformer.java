package com.bandari.naveen.samplemd.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by suresh on 12/3/18.
 */

public class MyPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {

        if (position < 0) {
//            ViewHelper.setPivotX(view, view.getMeasuredWidth());
//            ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
//            ViewHelper.setScaleY(view, 1 + (float) (position * 0.2));
//            ViewHelper.setRotationY(view, 20f * position);
            ViewHelper.setAlpha(view, position + 1);

        } else if (position < 1) {
            view.setTranslationX(view.getMeasuredWidth() * -position);
//            ViewHelper.setPivotX(view, 0);
//            ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
//            ViewHelper.setRotationY(view, 20f * position);
//            ViewHelper.setAlpha(view, 1 - position);
        }

    }
}
