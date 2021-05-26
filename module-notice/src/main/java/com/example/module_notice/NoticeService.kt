package com.example.module_notice

import com.example.library_network.Api
import retrofit2.http.GET

interface NoticeService {
    @GET(Api.THEME)
    suspend fun getTheme()

    @GET(Api.PUSH)
    suspend fun getPush()

    @GET(Api.INTERACTIVE)
    suspend fun getInteractive()
}