package com.francis.paging.ui.paging.recyclerview

import androidx.paging.DataSource
import com.francis.paging.db.UserModel

class AdapterDataSourceFactory : DataSource.Factory<Int, UserModel>() {
    override fun create(): DataSource<Int, UserModel> {
        val dataSource = AdapterDataSource()
        return dataSource
    }
}