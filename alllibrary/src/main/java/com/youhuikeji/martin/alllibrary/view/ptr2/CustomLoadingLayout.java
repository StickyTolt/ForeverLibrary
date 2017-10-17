package com.youhuikeji.martin.alllibrary.view.ptr2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.youhuikeji.martin.alllibrary.R;
import com.youhuikeji.martin.alllibrary.util.systemUtil.ScreenUtil;


/**
 * Created by sunpingji on 15/9/28.
 */
public class CustomLoadingLayout extends LoadingLayout {

    private FrameLayout mInnerLayout;
    private LoadingView loadingView;

    public CustomLoadingLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_custom, this);
        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
        loadingView = (LoadingView) mInnerLayout.findViewById(R.id.custom_loading);
    }

    @Override
    public void hideAllViews() {
        if (View.VISIBLE == loadingView.getVisibility()) {
            loadingView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPull(float scaleOfLayout) {
        loadingView.setBaseX(scaleOfLayout);
    }

    @Override
    protected void pullToRefresh() {

    }

    @Override
    public void refreshing() {
        loadingView.setNeedAnimation(true);
    }

    @Override
    protected void releaseToRefresh() {

    }

    @Override
    public void reset() {
        loadingView.setNeedAnimation(false);
        loadingView.reset();
    }

    @Override
    protected void showInvisibleViews() {
        if (View.INVISIBLE == loadingView.getVisibility()) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getContentSize() {
        return ScreenUtil.dip2px(30);
    }


    @Override
    public void setTextColor(ColorStateList color) {

    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {

    }

    @Override
    public void setLoadingDrawable(Drawable drawable) {

    }

    @Override
    public void setPullLabel(CharSequence pullLabel) {

    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {

    }

    @Override
    public void setReleaseLabel(CharSequence releaseLabel) {

    }

    @Override
    public void setTextTypeface(Typeface tf) {

    }
}