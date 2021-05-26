package com.example.module_home.net

import com.example.module_home.bean.DailyBean
import com.example.module_home.bean.HRecommendBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository private constructor(private val network: HomeNetwork){

    suspend fun getHomeRecommend(page: Int): HRecommendBean.HRecommend {
        return withContext(Dispatchers.IO){
            network.getHomeRecommend(page)
        }
    }

    suspend fun getDaily(num: Int): DailyBean.Daily{
        return withContext(Dispatchers.IO){
            network.getDaily(num)
        }
    }

    suspend fun getFind() = withContext(Dispatchers.IO){
        network.getFind()
    }

    companion object{
        private lateinit var repository: HomeRepository

        fun getInstance(network: HomeNetwork): HomeRepository {
            if (!Companion::repository.isInitialized) {
                synchronized(HomeRepository::class.java) {
                    if (!Companion::repository.isInitialized) {
                        repository =
                            HomeRepository(network)
                    }
                }
            }
            return repository!!
        }
    }
}