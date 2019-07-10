package com.francis.paging.dagger

import com.francis.paging.ui.MainActivity
import com.francis.paging.ui.paging.PagingActivity
import com.francis.paging.ui.paging.recyclerview.AdapterDataSource
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inJect(input: MainActivity)
    fun inJect(input: PagingActivity)
    fun inJect(input: AdapterDataSource)
}