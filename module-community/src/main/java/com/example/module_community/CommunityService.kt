package com.example.module_community

import com.example.library_network.Api
import retrofit2.http.GET

interface CommunityService {
    @GET(Api.FOCUS)
    suspend fun getFocus()

    @GET(Api.COMMUNITY_RECOMMEND)
    suspend fun getCommunity()
}