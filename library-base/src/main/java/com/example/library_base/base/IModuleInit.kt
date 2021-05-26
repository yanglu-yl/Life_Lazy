package com.example.library_base.base

import android.app.Application

interface IModuleInit {

    fun onInit(application: Application): Boolean

    fun onTerminate(): Boolean
}