package com.martin.alllibrary.base.mvp.factory;

import com.martin.alllibrary.base.mvp.presenter.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 作者：Martin on 2017/12/16 14:46
 * 邮箱：martin0207mfh@163.com
 *
 * @description 标注创建Presenter 的注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {

    Class<? extends BaseMvpPresenter> value();

}
