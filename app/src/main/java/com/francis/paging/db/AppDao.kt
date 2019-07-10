package com.francis.paging.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: UserModel)

    @Query("select * from UserModel")
    fun getAll(): MutableList<UserModel>

    @Query("SELECT COUNT(id) FROM UserModel")
    fun sizeOfDb(): Int


    @Query("SELECT * FROM UserModel WHERE id BETWEEN(:mFrom) AND(:mTo)")
    fun getDataPosition(mFrom: Int, mTo: Int): MutableList<UserModel>

}