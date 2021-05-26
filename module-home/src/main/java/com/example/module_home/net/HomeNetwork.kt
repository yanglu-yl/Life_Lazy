package com.example.module_home.net

import com.example.library_network.RetrofitClient

class HomeNetwork {

    private val homeService by lazy { RetrofitClient.getInstance().create(HomeService::class.java) }

    suspend fun getHomeRecommend(page: Int) = homeService.getHomeRecommend(page)

    suspend fun getDaily(num: Int) = homeService.getDaily(num)

    suspend fun getFind() = homeService.getFind()

    companion object{
        private var network: HomeNetwork?= null

        fun getInstance(): HomeNetwork {
            if (network == null) {
                synchronized(HomeNetwork::class.java) {
                    if (network == null) {
                        network =
                            HomeNetwork()
                    }
                }
            }
            return network!!
        }
    }

}