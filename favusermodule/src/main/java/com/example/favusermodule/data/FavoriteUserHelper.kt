package com.example.favusermodule.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.AVATAR_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.FOLLOWERS_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.FOLLOWING_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.HTML_URL
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.ID
import com.example.favusermodule.data.DatabaseContract.FavoriteUserColumns.Companion.LOGIN
import com.example.favusermodule.model.User
import java.sql.SQLException

class FavoriteUserHelper(context: Context) {

    val DATABASE_TABLE = DatabaseContract().TABLE_USER
    var databaseHelper = DBUserHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile private var INSTANCE: FavoriteUserHelper? = null

        fun getInstance(context: Context): FavoriteUserHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoriteUserHelper(context)
            }

    }

    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }
    fun close(){
        databaseHelper.close()

        if(database.isOpen)
            database.close()
    }

    fun getUSer(): ArrayList<User> {
        val arrayList = ArrayList<User>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            null, null
        )
        cursor.moveToFirst()
        var user : User
        if (cursor.count > 0) {
            do {
                user = User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(LOGIN)),
                    cursor.getString(cursor.getColumnIndexOrThrow(AVATAR_URL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(HTML_URL)),
//                    cursor.getInt(cursor.getColumnIndexOrThrow(FOLLOWERS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(FOLLOWERS_URL)),
//                    cursor.getInt(cursor.getColumnIndexOrThrow(FOLLOWING)),
                    cursor.getString(cursor.getColumnIndexOrThrow(FOLLOWING_URL))
//                    cursor.getString(cursor.getColumnIndexOrThrow(BIO))
                )

                arrayList.add(user)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }


    @SuppressLint("Recycle")
    fun isUserFavorited(id: String): Boolean {
        val cursor = database.query(
            DATABASE_TABLE,
            null, "$ID = '$id'", null, null, null,
            null, null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            return true
        }
        return false

    }

    fun insertUser(user: User): Long {
        val args = ContentValues()
        args.put(ID, user.id)
        args.put(LOGIN, user.login)
        args.put(AVATAR_URL, user.avatarUrl)
        args.put(HTML_URL, user.htmlUrl)
//        args.put(FOLLOWERS, user.followers)
        args.put(FOLLOWERS_URL, user.followersUrl)
//        args.put(FOLLOWING, user.following)
        args.put(FOLLOWING_URL, user.followingUrl)
//        args.put(BIO, user.bio)


        return database.insert(DATABASE_TABLE, null, args)
    }

   fun deleteUser(id: String): Int {
        return database.delete(DatabaseContract().TABLE_USER, "$ID = '$id'", null)
    }

//

    fun queryProvider(): Cursor {
        return database.query(DATABASE_TABLE, null, null, null, null,
            null, "$ID ASC")
    }

    fun updateProvider(id: String, values: ContentValues): Int {
        return database.update(DATABASE_TABLE, values, "$ID = ?", arrayOf(id))
    }

    fun deleteProvider(id: String): Int {
        return database.delete(DATABASE_TABLE, "$ID = ?", arrayOf(id))
    }

    fun insertProvider(values: ContentValues): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun queryByIdProvider(id: String): Cursor {
        return database.query(DATABASE_TABLE, null, "$ID = ?", arrayOf(id), null,
            null, null, null)
    }
}