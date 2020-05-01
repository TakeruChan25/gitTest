package com.example.ssbbmm5.api

import com.example.ssbbmm5.model.User
import com.example.ssbbmm5.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Service{

    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String?
    ): Call<User>

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/followers")
    fun getFollowers(): Call<List<User>>

    @GET("users/following")
    fun getFollowing(): Call<List<User>>

//    @GET("/search/users")
//    fun getItems(): Call<UserResponse?>?
//
//    @GET
//    fun getItem()

//    @GET("/users")
//    fun getItems(
//        @Query("per_page") perPage: Int,
//        @Query("page") page: Int
//    ): Call<List<User>>

//    @GET("users/{username}")
//    fun getUser(@Path("username") username: String?): Call<User>

//    @GET("group/{id}/users")
//    fun groupList(
//        @Path("id") groupId: Int,
//        @Query("sort") sort: String?
//    ): Call<List<User>>
}