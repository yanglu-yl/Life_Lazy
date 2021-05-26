package com.example.module_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.library_base.BaseFragment
import com.example.library_base.router.RouterFragmentPath
import com.example.module_user.databinding.FragmentUserBinding

@Route(path = RouterFragmentPath.User.PAGER_ME)
class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun setView(): FragmentUserBinding {
        return FragmentUserBinding.inflate(inflater!!, container!!, false)
    }

    override fun onViewCreat() {

    }

    override fun init() {

    }

    override fun showToast() {

    }

}