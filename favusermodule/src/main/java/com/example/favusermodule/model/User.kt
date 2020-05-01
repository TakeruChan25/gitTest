package com.example.favusermodule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("id")
    val id: Int,

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("html_url")
    val htmlUrl: String,

//    @SerializedName("followers")
//    val followers: Int,

    @SerializedName("followers_url")
    val followersUrl: String,

//    @SerializedName("following")
//    val following: Int,

    @SerializedName("following_url")
    val followingUrl: String

//    @SerializedName("bio")
//    val bio: String

): Parcelable

//@Parcelize
//data class User(
//    var id: Int,
//    var login : String,
//    var avatarUrl : String
////    var followers : Int,
////    var followersUrl : String,
////    var following : Int,
////    var followingUrl : String,
////    var gistUrl : String
////    var bio : String
//): Parcelable
