package com.example.module_community.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.library_base.BaseFragment
import com.example.library_base.router.RouterFragmentPath
import com.example.module_community.R
import com.example.module_community.databinding.FragmentCommunityBinding


@Route(path = RouterFragmentPath.Community.PAGER_COMMUNITY)
class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {

    override fun setView(): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(inflater!!, container!!, false)
    }

    override fun onViewCreat() {

    }

    override fun init() {

    }

    override fun showToast() {

    }
}