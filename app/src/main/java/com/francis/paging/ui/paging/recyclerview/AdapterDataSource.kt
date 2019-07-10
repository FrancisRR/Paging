package com.francis.paging.ui.paging.recyclerview

import androidx.paging.PageKeyedDataSource
import com.francis.paging.base.AppController
import com.francis.paging.db.AppDatabase
import com.francis.paging.db.UserModel
import com.francis.paging.utils.UiUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AdapterDataSource : PageKeyedDataSource<Int, UserModel>() {

    private val TAG by lazy { AdapterDataSource::class.java.simpleName }
    @set:Inject
    var roomDb: AppDatabase? = null

    private var END_PAGE = 10
    private val START_PAGE = 0

    init {
        AppController.Dagger.inJect(this)
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserModel>) {
        callback.onResult(dbList(START_PAGE, END_PAGE), null, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserModel>) {
        val key = params.key
        callback.onResult(dbList(END_PAGE + 1, END_PAGE + 10), key)
        END_PAGE += 10
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserModel>) {

    }


    private fun dbList(startP: Int, endP: Int): MutableList<UserModel> = runBlocking {
        UiUtils.errorLog("Size", "start:$startP || end:${endP}")
        return@runBlocking GlobalScope.async {
            val res = roomDb?.appDao()?.getDataPosition(startP, endP)
            UiUtils.errorLog(TAG, res.toString())
            return@async res
        }.await()!!
    }
}