package com.example.module_home.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.library_base.BaseFragment
import com.example.library_base.router.RouterFragmentPath
import com.example.module_home.HomeViewModel
import com.example.module_home.InjectorUtil
import com.example.module_home.databinding.HomeFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


@Route(path = RouterFragmentPath.Home.PAGER_HOME)
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    override fun setView(): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater!!, container!!, false)
    }

    private val viewModel: HomeViewModel  by viewModels<HomeViewModel>(
        { requireActivity() },
        { InjectorUtil.getHomeModel() })

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreat() {

        view!!.tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
        view!!.tabLayout.isFocusableInTouchMode = false

        view!!.viewPager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when(position){
                    0 -> FindFragment()
                    1 -> RecommendFragment()
                    else -> DailyFragment()
                }
            }

        }

        view!!.viewPager.isSaveEnabled = false

        TabLayoutMediator(view!!.tabLayout, view!!.viewPager, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when (position) {
                    0 -> tab.setText("发现")
                    1 -> tab.setText("推荐")
                    else -> tab.setText("日报")
                }
            }

        }).attach()

        viewModel.getFind().observe(this, Observer {
            Log.d("hahaha", it.count.toString())
        })


    }

    override fun init() {

    }

    override fun showToast() {

    }


}