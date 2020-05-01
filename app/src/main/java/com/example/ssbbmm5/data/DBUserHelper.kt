package com.example.ssbbmm5.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

internal class DBUserHelper(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user"
        private const val DATABASE_VERSION = 1

        private val SQL_CREATE_TABLE_FAVORITE = String.format(
            "CREATE TABLE ${DatabaseContract().TABLE_USER}" +
                    "(${DatabaseContract.FavoriteUserColumns.ID} INTEGER PRIMARY KEY," +
                    "${DatabaseContract.FavoriteUserColumns.LOGIN} TEXT NOT NULL," +
//                "${DatabaseContract.FavoriteUserColumns.NAME} TEXT NOT NULL," +
                    "${DatabaseContract.FavoriteUserColumns.AVATAR_URL} TEXT NOT NULL," +
                    "${DatabaseContract.FavoriteUserColumns.HTML_URL} TEXT NOT NULL," +
//                "${DatabaseContract.FavoriteUserColumns.FOLLOWERS} TEXT NOT NULL," +
                    "${DatabaseContract.FavoriteUserColumns.FOLLOWERS_URL} TEXT NOT NULL," +
//                "${DatabaseContract.FavoriteUserColumns.FOLLOWING} TEXT NOT NULL," +
                    "${DatabaseContract.FavoriteUserColumns.FOLLOWING_URL} TEXT NOT NULL)"
//                "${DatabaseContract.FavoriteUserColumns.BIO} TEXT NOT NULL)"
        )
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_FAVORITE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + DatabaseContract().TABLE_USER)
    }
}