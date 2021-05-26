package com.example.module_home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_home.bean.DailyBean
import com.example.module_home.bean.FindBean
import com.example.module_home.bean.HRecommendBean
import com.example.module_home.net.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: HomeRepository) : ViewModel() {


    var findData = MutableLiveData<FindBean.Find>()
    var dailyData = MutableLiveData<DailyBean.Daily>()
    var hRecommendData = MutableLiveData<HRecommendBean.HRecommend>()

    fun getFind(): MutableLiveData<FindBean.Find>{
        launch({
            findData.value = repository.getFind() as FindBean.Find
        },{
            Log.e("ERROR",it.message.toString())
        })
        return findData
    }

    fun getDaily(num: Int): MutableLiveData<DailyBean.Daily>{
        launch({
            dailyData.value = repository.getDaily(num)
        },{
            Log.e("ERROR",it.message.toString())
        })
        return dailyData
    }

    fun getHomeRecommend(page: Int): MutableLiveData<HRecommendBean.HRecommend>{
        launch({
            hRecommendData.value = repository.getHomeRecommend(page)
        },{
            Log.e("ERROR",it.message.toString())
        })
        return hRecommendData
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        }catch (e: Throwable){
            error(e)
        }
    }

}