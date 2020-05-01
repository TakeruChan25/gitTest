package com.example.ssbbmm5.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse (
    @SerializedName("items")
    val items: List<User>?
) : Parcelable