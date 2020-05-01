package com.example.ssbbmm5.ui.home

import com.example.ssbbmm5.api.Interface
import com.example.ssbbmm5.model.User
import com.example.ssbbmm5.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private val homeView: HomeView) {

    fun setUser() {
        Interface.create()
            .getAllUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    homeView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response.isSuccessful) {
                        homeView.onDataCompleteFromApi(response.body())
                    }
                }
            })
    }

    fun searchUser(q: String) {
        Interface.create()
            .searchUser(q)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    homeView.onDataErrorFromApi(t)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        homeView.onDataCompleteFromApi(data?.items)
                    }
                }

            })
    }

}