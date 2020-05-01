package com.example.ssbbmm5.ui.home

import com.example.ssbbmm5.model.User

interface HomeView {
    fun onDataCompleteFromApi(data: List<User>?)
    fun onDataErrorFromApi(throwable: Throwable)
}