package com.francis.paging.dagger

import androidx.room.Room
import com.francis.paging.base.AppController
import com.francis.paging.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val controller: AppController) {


    @Singleton
    @Provides
    public fun getDb(): AppDatabase {
        return Room.databaseBuilder(controller, AppDatabase::class.java, "MyDb").build()
    }

}