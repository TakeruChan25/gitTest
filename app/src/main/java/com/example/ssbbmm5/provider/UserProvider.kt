package com.example.ssbbmm5.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.ssbbmm5.data.DatabaseContract
import com.example.ssbbmm5.data.DatabaseContract.FavoriteUserColumns.Companion.ID
import com.example.ssbbmm5.data.FavoriteUserHelper

class UserProvider : ContentProvider() {

        private val USER = 1
        private val USER_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        lateinit var favoriteUserHelper: FavoriteUserHelper


    private fun uriMatching(){
        sUriMatcher.addURI(DatabaseContract().AUTHORITY, DatabaseContract().TABLE_USER, USER)
        sUriMatcher.addURI(DatabaseContract().AUTHORITY, DatabaseContract().TABLE_USER + "/$ID", USER_ID)

    }

    override fun onCreate(): Boolean {
        uriMatching()
        favoriteUserHelper = FavoriteUserHelper.getInstance(context!!)

        return true
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
       var updated = 0

        when(sUriMatcher.match(uri)) {
            USER_ID -> {
                favoriteUserHelper.open()
                updated = favoriteUserHelper.updateProvider(
                    uri.lastPathSegment as String,
                    values as ContentValues
                )
            }
        }
        return updated
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var uriResult : Uri? = null

        when(sUriMatcher.match(uri)) {
            USER -> {
                favoriteUserHelper.open()
                val addedUser = favoriteUserHelper.insertProvider(values as ContentValues)
                uriResult =
                    Uri.parse(DatabaseContract.FavoriteUserColumns.CONTENT_URI.toString() + "/" + addedUser)
            }
        }
        return uriResult
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var deleted = 0

        when(sUriMatcher.match(uri)) {
            USER_ID -> {
                favoriteUserHelper.open()
                deleted = favoriteUserHelper.deleteProvider(uri.lastPathSegment!!)
            }
        }
        return deleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        favoriteUserHelper.open()
        return when (sUriMatcher.match(uri)) {
            USER -> favoriteUserHelper.queryProvider()
            USER_ID -> favoriteUserHelper.queryByIdProvider(uri.lastPathSegment as String)
            else -> null
        }
    }

}
