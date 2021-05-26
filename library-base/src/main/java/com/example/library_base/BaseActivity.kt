package com.example.library_base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.library_base.ui.IBaseView
import com.example.library_base.utils.ActivityManager

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), IBaseView{

    protected var view: B ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //将背景图与系统状态栏融合到一起
        if (Build.VERSION.SDK_INT >= 21) {
            var decorView = window.decorView
            var option = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            decorView.systemUiVisibility = option
            window.setNavigationBarColor(Color.TRANSPARENT);
            window.statusBarColor = Color.TRANSPARENT
        }

        view = setView()
        setContentView(view!!.root)

        ActivityManager.addActivity(this)   //创建Activity入栈管理

        onCreat()
    }

    protected abstract fun setView(): B

    protected abstract fun onCreat()

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)    //销毁Activity移出栈
    }

}