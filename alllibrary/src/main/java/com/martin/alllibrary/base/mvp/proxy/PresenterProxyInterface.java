package com.martin.alllibrary.base.mvp.proxy;

import com.martin.alllibrary.base.mvp.view.BaseMvpView;
import com.martin.alllibrary.base.mvp.factory.PresenterMvpFactory;
import com.martin.alllibrary.base.mvp.presenter.BaseMvpPresenter;

/**
 * 作者：Martin on 2017/12/16 15:03
 * 邮箱：martin0207mfh@163.com
 *
 * @description 代理接口
 */
public interface PresenterProxyInterface<V extends BaseMvpView, P extends BaseMvpPresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    PresenterMvpFactory<V, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getPresenter();

}
