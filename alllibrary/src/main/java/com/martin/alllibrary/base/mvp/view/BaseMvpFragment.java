package com.martin.alllibrary.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.alllibrary.base.mvc.BaseFragment;
import com.martin.alllibrary.base.mvp.factory.PresenterMvpFactory;
import com.martin.alllibrary.base.mvp.factory.PresenterMvpFactoryImpl;
import com.martin.alllibrary.base.mvp.presenter.BaseMvpPresenter;
import com.martin.alllibrary.base.mvp.proxy.BaseMvpProxy;
import com.martin.alllibrary.base.mvp.proxy.PresenterProxyInterface;

/**
 * 作者：Martin on 2018/1/2 13:43
 * 邮箱：martin0207mfh@163.com
 */
public class BaseMvpFragment<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends BaseFragment implements PresenterProxyInterface<V, P> {

    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }
}
