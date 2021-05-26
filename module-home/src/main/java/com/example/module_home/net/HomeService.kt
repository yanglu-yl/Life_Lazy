package com.example.module_home.net

import com.example.library_network.Api
import com.example.module_home.bean.DailyBean
import com.example.module_home.bean.FindBean
import com.example.module_home.bean.HRecommendBean
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET(Api.HOME_RECOMMEND)
    suspend fun getHomeRecommend(@Query("page") page: Int) : HRecommendBean.HRecommend

    @GET(Api.DAILY)
    suspend fun getDaily(@Query("num") num: Int) : DailyBean.Daily

    @GET(Api.FIND)
    suspend fun getFind(): FindBean.Find
}