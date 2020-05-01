package com.example.favusermodule.model

import android.os.Parcelable
import com.example.favusermodule.model.User
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse (
    @SerializedName("items")
    val items: List<User>
) : Parcelable