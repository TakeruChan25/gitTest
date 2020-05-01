package com.example.ssbbmm5.ui.detail.tab

import com.example.ssbbmm5.model.User

interface TabView {
    fun onDataCompleteFromApi(data: List<User>?)
    fun onDataErrorFromApi(throwable: Throwable)
}