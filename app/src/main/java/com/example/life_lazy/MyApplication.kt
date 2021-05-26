package com.example.life_lazy

import android.app.Application
import com.example.library_base.base.BaseModuleInit

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        BaseModuleInit().onInit(this)

    }

    override fun onTerminate() {
        super.onTerminate()
        BaseModuleInit().onTerminate()
    }
}