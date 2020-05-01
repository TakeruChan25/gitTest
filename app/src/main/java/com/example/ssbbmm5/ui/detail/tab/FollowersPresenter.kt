package com.example.ssbbmm5.ui.detail.tab

import com.example.ssbbmm5.api.Interface
import com.example.ssbbmm5.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersPresenter(private val tabView: TabView) {

    fun setFollowers(){
        Interface.create()
            .getFollowers()
            .enqueue(object : Callback<List<User>>{
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    tabView.onDataErrorFromApi(t)
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful){
                        tabView.onDataCompleteFromApi(response.body())
                    }
                }
            })
    }

    fun setFollowing(){
        Interface.create()
            .getFollowing()
            .enqueue(object : Callback<List<User>>{
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    tabView.onDataErrorFromApi(t)
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful){
                        tabView.onDataCompleteFromApi(response.body())
                    }
                }
            })
    }
}