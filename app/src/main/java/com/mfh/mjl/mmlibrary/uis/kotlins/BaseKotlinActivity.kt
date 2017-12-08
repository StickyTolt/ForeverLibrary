package com.mfh.mjl.mmlibrary.uis.kotlins

import android.os.Bundle
import com.martin.alllibrary.base.BaseActivity

/**
 * 作者：Martin on 2017/12/8 14:51
 * 邮箱：martin0207mfh@163.com
 * TODO: must include this layout statement
 *              TODO :import kotlinx.android.synthetic.main."this layout".*
 */
abstract class BaseKotlinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        init(savedInstanceState);
    }

    /**
     * 设置布局 Id
     */
    protected abstract fun setLayoutId(): Int

    /**
     * 进行 Activity 的初始化
     */
    abstract fun init(savedInstanceState: Bundle?)

}