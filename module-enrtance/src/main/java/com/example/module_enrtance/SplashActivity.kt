package com.example.module_enrtance

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.library_base.BaseActivity
import com.example.module_enrtance.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun setView(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreat() {

        view!!.animLogo.addGradientAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                Log.d("AnimLogoView", "Gradient anim end")
            }
        })

        view!!.animLogo.addOffestAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                Log.d("AnimLogoView", "Offset anim end")
            }
        })
        view!!.animLogo.startAnimation()

        var animSet = AnimatorSet()
        var icon_al = ObjectAnimator.ofFloat(view!!.icon, "alpha", 0f, 1f)
        var moveIn = ObjectAnimator.ofFloat(view!!.city, "translationX", -500f, 0f)
        var animator = ObjectAnimator.ofFloat(view!!.city, "alpha", 0f, 1f)
        animSet.play(moveIn).with(animator).with(icon_al)
        animSet.setDuration(3000)
        animSet.start()

        Handler().postDelayed(object : Runnable{
            override fun run() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }, 5*1000)
    }

    override fun init() {

    }

    override fun showToast() {

    }
}