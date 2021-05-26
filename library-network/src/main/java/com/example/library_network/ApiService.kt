package com.example.library_network

import retrofit2.http.GET

interface ApiService {

    @GET(Api.PLAY_DETAIL)
    suspend fun getPlayDetail()

    @GET(Api.RELATE_VIDEO)
    suspend fun getRelateVideo()

    @GET(Api.COMMENT_LIST)
    suspend fun getCommentList()

}