package com.example.favusermodule.data.mapping

import android.database.Cursor
import android.util.Log
import com.example.favusermodule.data.DatabaseContract
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.AVATAR_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.FOLLOWERS_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.FOLLOWING_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.HTML_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.ID
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.LOGIN
import com.example.favusermodule.model.User

object MappingHelper{
    fun mapCursorToArrayList(userCursor: Cursor): ArrayList<User> {
        val userList = ArrayList<User>()
        while (userCursor.moveToNext()){
            val id =
                userCursor.getInt(userCursor.getColumnIndexOrThrow(ID))
            val login =
                userCursor.getString(userCursor.getColumnIndexOrThrow(LOGIN))
            val avatarUrl =
                userCursor.getString(userCursor.getColumnIndexOrThrow(AVATAR_URL))
            val htmlUrl =
                userCursor.getString(userCursor.getColumnIndexOrThrow(HTML_URL))
//            val followers =
//                userCursor.getInt(userCursor.getColumnIndexOrThrow(FOLLOWERS))
            val followersUrl =
                userCursor.getString(userCursor.getColumnIndexOrThrow(FOLLOWERS_URL))
//            val following =
//                userCursor.getInt(userCursor.getColumnIndexOrThrow(FOLLOWING))
            val followingUrl =
                userCursor.getString(userCursor.getColumnIndexOrThrow(FOLLOWING_URL))
//            val bio =
//                userCursor.getString(userCursor.getColumnIndexOrThrow(BIO))
            userList.add(
                User(
                    id,
                    login,
                    avatarUrl,
                    htmlUrl,
//                    followers,
                    followersUrl,
//                    following,
                    followingUrl
//                    bio
                )
            )
        }
        return userList
    }
}