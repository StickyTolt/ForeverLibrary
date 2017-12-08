package com.mfh.mjl.mmlibrary.uis.kotlins.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.mfh.mjl.mmlibrary.R
import com.mfh.mjl.mmlibrary.uis.kotlins.BaseKotlinActivity

import kotlinx.android.synthetic.main.activity_first_kotlin.*

/**
 * 作者：Martin on 2017/12/7 15:44
 * 邮箱：martin0207mfh@163.com
 */
class FirstKotlinActivity : BaseKotlinActivity() {

    val TAG: String? = FirstKotlinActivity::class.simpleName;

    companion object {
        fun Context.start() {
            this.startActivity(Intent(this, FirstKotlinActivity::class.java))
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_first_kotlin
    }

    @SuppressLint("SetTextI18n")
    override fun init(savedInstanceState: Bundle?) {
        btn_toast.setOnClickListener { showToast("这是点击之后的效果") }
        btn_dialog.setOnClickListener({ showProgress("这是另一种点击效果") })
        in_top.findViewById<TextView>(R.id.txt_top_title).setText("我的第一个Kotlin")
        in_top.findViewById<ImageView>(R.id.img_top_back).setOnClickListener { finish() }
    }

}