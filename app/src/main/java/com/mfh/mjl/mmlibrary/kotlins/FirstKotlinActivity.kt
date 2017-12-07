package com.mfh.mjl.mmlibrary.kotlins

import kotlinx.android.synthetic.main.activity_first_kotlin.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.martin.alllibrary.base.BaseActivity
import com.mfh.mjl.mmlibrary.R

/**
 * 作者：Martin on 2017/12/7 15:44
 * 邮箱：martin0207mfh@163.com
 */
class FirstKotlinActivity : BaseActivity() {

    val TAG: String? = FirstKotlinActivity::class.simpleName;

    companion object {
        fun Context.start() {
            this.startActivity(Intent(this, FirstKotlinActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowStatusBarColor(R.color.main_color)
        setContentView(R.layout.activity_first_kotlin)

        initView();
    }

    private fun initView() {
        btn_toast.setOnClickListener { showToast("这是点击之后的效果") }
        btn_dialog.setOnClickListener({ showProgress("这是另一种点击效果") })
    }


}