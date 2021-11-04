package com.nikitabolshakov.libraries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getAll(): List<User>

    @Insert
    fun insertAll(vararg user: User)

    @Delete
    fun delete(user: User)
}