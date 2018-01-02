package com.martin.alllibrary.base.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.alllibrary.base.mvp.view.BaseMvpView;
import com.martin.alllibrary.util.showUtil.LogUtils;

/**
 * 作者：Martin on 2017/12/15 22:47
 * 邮箱：martin0207mfh@163.com
 *
 * @description MVP 架构的 Presenter基类,使用MVP结构时,必须继承该类
 * 指定绑定的View层,必须实现 BaseMvpView
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView> {

    private static final String TAG = BaseMvpPresenter.class.getSimpleName();

    private BaseMvpView mvpView;

    /**
     * Presenter 被创建之后 调用
     *
     * @param savedState 被意外销毁后的 Bundle
     */
    public void onCreatePresenter(@Nullable Bundle savedState) {
        LogUtils.e(TAG, "create presenter");
    }

    /**
     * 绑定 V 层
     */
    public void onAttachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 解绑 V 层
     */
    public void onDetachView() {
        this.mvpView = null;
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
    }

    /**
     * 获取 V 层
     */
    public BaseMvpView getView() {
        return mvpView;
    }
}
