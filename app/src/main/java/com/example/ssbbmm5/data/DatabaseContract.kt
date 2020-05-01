package com.example.ssbbmm5.data

import android.net.Uri
import android.provider.BaseColumns

internal class DatabaseContract {

    var TABLE_USER = "user"

    val AUTHORITY = "com.example.ssbbmm5"
    val SCHEME = "content"

    internal class FavoriteUserColumns: BaseColumns {
        companion object{
            var ID = "_id"
            var LOGIN = "login"
            var NAME = "name"
            var HTML_URL = "html_url"
            var AVATAR_URL = "avatar_url"
//            var FOLLOWERS = "followers"
            var FOLLOWERS_URL = "followers_url"
//            var FOLLOWING = "following"
            var FOLLOWING_URL = "following_url"
//            var BIO = "bio"

            val CONTENT_URI: Uri = Uri.Builder().scheme(DatabaseContract().SCHEME)
                .authority(DatabaseContract().AUTHORITY)
                .appendPath(DatabaseContract().TABLE_USER)
                .build()
        }
    }
}