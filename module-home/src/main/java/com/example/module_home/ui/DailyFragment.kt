package com.example.module_home.ui

import com.example.library_base.BaseFragment
import com.example.module_home.databinding.DailyFragmentBinding

class DailyFragment : BaseFragment<DailyFragmentBinding>(){

    override fun setView(): DailyFragmentBinding {
        return DailyFragmentBinding.inflate(inflater!!, container, false)
    }

    override fun onViewCreat() {

    }

    override fun init() {

    }

    override fun showToast() {

    }


}