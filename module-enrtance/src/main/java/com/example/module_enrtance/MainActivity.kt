package com.example.module_enrtance

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.example.library_base.BaseActivity
import com.example.library_base.router.RouterFragmentPath
import com.example.module_enrtance.databinding.ActivityMain1Binding

class MainActivity : BaseActivity<ActivityMain1Binding>() {

    var mFragment = mutableListOf<Fragment>()

    private fun initFragment(){
        var homeFragment = ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation(this, object : NavCallback(){
            override fun onArrival(postcard: Postcard?) {
                Log.e("minfo", "页面跳转完成")
            }

            override fun onFound(postcard: Postcard?) {
                Log.e("minfo", "页面找到")
            }

            override fun onLost(postcard: Postcard?) {
                Log.e("minfo", "页面找bu到")
            }

            override fun onInterrupt(postcard: Postcard?) {
                Log.e("minfo", "页面被拦截")
            }
        }) as Fragment
        var communityFragment = ARouter.getInstance().build(RouterFragmentPath.Community.PAGER_COMMUNITY).navigation() as Fragment
        var noticeFragment = ARouter.getInstance().build(RouterFragmentPath.Notice.PAGER_NOTICE).navigation() as Fragment
        var userFragment = ARouter.getInstance().build(RouterFragmentPath.User.PAGER_ME).navigation() as Fragment

        mFragment!!.add(homeFragment)
        mFragment!!.add(communityFragment)
        mFragment!!.add(noticeFragment)
        mFragment!!.add(userFragment)

        if (homeFragment != null) {
            var transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frameLayout1 , homeFragment)
            transaction.commitAllowingStateLoss()
        }
    }

    private fun setFragments(index: Int) {
        var currentFragment = mFragment!!.get(index)
        if (currentFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout1, currentFragment).commitAllowingStateLoss()
        }
    }

    override fun setView(): ActivityMain1Binding {
        return ActivityMain1Binding.inflate(layoutInflater)
    }

    override fun onCreat() {

        setStatusTextColor(true)

        initFragment()

        view!!.nav1.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_home -> setFragments(0)
                R.id.bottom_menu_community -> setFragments(1)
                R.id.bottom_menu_notice -> setFragments(2)
                R.id.bottom_menu_user -> setFragments(3)
            }
            true
        }


    }

    override fun init() {

    }

    override fun showToast() {

    }

    private fun setStatusTextColor(isGray: Boolean){
        if (isGray){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }else{
            window.decorView.systemUiVisibility = 0
        }
    }
}