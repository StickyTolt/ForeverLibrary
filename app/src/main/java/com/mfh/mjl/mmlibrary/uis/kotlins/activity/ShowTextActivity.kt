package com.mfh.mjl.mmlibrary.uis.kotlins.activity

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.martin.alllibrary.extras.ExtraName
import com.mfh.mjl.mmlibrary.R
import com.mfh.mjl.mmlibrary.uis.kotlins.BaseKotlinActivity
import kotlinx.android.synthetic.main.activity_show_text.*

/**
 * 作者：Martin on 2017/12/8 14:33
 * 邮箱：martin0207mfh@163.com
 */
class ShowTextActivity : BaseKotlinActivity() {

    companion object {
        fun Context.start(msg: String) {
            var intent = android.content.Intent(this, ShowTextActivity::class.java)
            intent.putExtra(ExtraName.EXTRA_DATA, msg)
            this.startActivity(intent)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_show_text
    }

    override fun init(savedInstanceState: Bundle?) {
        var data = intent.getStringExtra(ExtraName.EXTRA_DATA)
        txt_content.text = data

        in_top.findViewById<TextView>(R.id.txt_top_title).text = "扫描内容展示"
        in_top.findViewById<ImageView>(R.id.img_top_back).setOnClickListener { finish() }
    }
}