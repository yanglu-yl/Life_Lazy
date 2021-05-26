package com.example.module_home

import com.example.module_home.net.HomeNetwork
import com.example.module_home.net.HomeRepository

object InjectorUtil {

    private fun getHomeRepository() = HomeRepository.getInstance(HomeNetwork.getInstance())

    fun getHomeModel() = HomeModeFactory(getHomeRepository())
}