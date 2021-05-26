package com.example.module_notice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.library_base.BaseFragment
import com.example.library_base.router.RouterFragmentPath
import com.example.module_notice.R
import com.example.module_notice.databinding.FragmentNoticeBinding

@Route(path = RouterFragmentPath.Notice.PAGER_NOTICE)
class NoticeFragment : BaseFragment<FragmentNoticeBinding>() {

    override fun setView(): FragmentNoticeBinding {
        return FragmentNoticeBinding.inflate(inflater!!, container!!, false)
    }

    override fun onViewCreat() {

    }

    override fun init() {

    }

    override fun showToast() {

    }
}