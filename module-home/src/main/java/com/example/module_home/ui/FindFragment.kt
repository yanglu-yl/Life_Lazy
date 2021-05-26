package com.example.module_home.ui

import com.example.library_base.BaseFragment
import com.example.module_home.databinding.FindFragmentBinding

class FindFragment : BaseFragment<FindFragmentBinding>(){

    override fun setView(): FindFragmentBinding {
        return FindFragmentBinding.inflate(inflater!!, container, false)
    }

    override fun onViewCreat() {

    }

    override fun init() {

    }

    override fun showToast() {

    }
}