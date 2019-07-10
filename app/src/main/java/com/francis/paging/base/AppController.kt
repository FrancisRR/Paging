package com.francis.paging.base

import android.app.Application
import com.francis.paging.dagger.AppComponent
import com.francis.paging.dagger.AppModule
import com.francis.paging.dagger.DaggerAppComponent

class AppController : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        Dagger = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    companion object {
        public lateinit var instance: AppController
        public lateinit var Dagger: AppComponent
    }

}