package com.example.library_base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.library_base.ui.IBaseView

abstract class BaseFragment<B : ViewBinding> : Fragment(), IBaseView{

    protected var view: B ?= null
    protected var inflater: LayoutInflater ?= null
    protected var container: ViewGroup ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater
        this.container = container
        view = setView()
        return view!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreat()
    }

    protected abstract fun setView(): B

    protected abstract fun onViewCreat()

    override fun onDestroyView() {
        super.onDestroyView()
        view = null
    }
}