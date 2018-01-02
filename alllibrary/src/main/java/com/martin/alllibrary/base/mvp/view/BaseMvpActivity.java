package com.martin.alllibrary.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.alllibrary.base.mvc.BaseActivity;
import com.martin.alllibrary.base.mvp.presenter.BaseMvpPresenter;
import com.martin.alllibrary.base.mvp.proxy.BaseMvpProxy;
import com.martin.alllibrary.base.mvp.factory.PresenterMvpFactory;
import com.martin.alllibrary.base.mvp.factory.PresenterMvpFactoryImpl;
import com.martin.alllibrary.base.mvp.proxy.PresenterProxyInterface;
import com.martin.alllibrary.util.showUtil.LogUtils;

/**
 * 作者：Martin on 2017/12/15 22:55
 * 邮箱：martin0207mfh@163.com
 */
public abstract class BaseMvpActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends BaseActivity
        implements PresenterProxyInterface<V, P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("perfect-mvp", "V onCreate");
        LogUtils.e("perfect-mvp", "V onCreate mProxy = " + mProxy);
        LogUtils.e("perfect-mvp", "V onCreate this = " + this.hashCode());
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("perfect-mvp", "V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("perfect-mvp", "V onDestroy = ");
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        LogUtils.e("perfect-mvp", "V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        LogUtils.e("perfect-mvp", "V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        LogUtils.e("perfect-mvp", "V getMvpPresenter");
        return mProxy.getPresenter();
    }
}
