package com.martin.alllibrary.base.mvp.factory;

import com.martin.alllibrary.base.mvp.view.BaseMvpView;
import com.martin.alllibrary.base.mvp.presenter.BaseMvpPresenter;

/**
 * 作者：Martin on 2017/12/16 14:44
 * 邮箱：martin0207mfh@163.com
 *
 * @description Presenter 工厂接口
 */
public interface PresenterMvpFactory<V extends BaseMvpView, P extends BaseMvpPresenter<V>> {

    /**
     * 创建Presenter 的接口
     */
    P createPresenter();

}
