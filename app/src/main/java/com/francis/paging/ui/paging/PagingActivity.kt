package com.francis.paging.ui.paging

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.francis.paging.R
import com.francis.paging.base.AppController
import com.francis.paging.base.BaseActivity
import com.francis.paging.db.AppDatabase
import com.francis.paging.db.UserModel
import com.francis.paging.ui.paging.recyclerview.Adapter
import com.francis.paging.ui.paging.recyclerview.AdapterDataSourceFactory
import kotlinx.android.synthetic.main.activity_paging.*
import javax.inject.Inject

class PagingActivity : BaseActivity() {

    private val TAG by lazy { PagingActivity::class.java.simpleName }
    @set:Inject
    var roomDb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        AppController.Dagger.inJect(this)

        createAdapter()
    }


    private fun createAdapter() {
        recycler.layoutManager = LinearLayoutManager(this)
        val adp = Adapter(this)
        recycler.adapter = adp


        val factory:AdapterDataSourceFactory = AdapterDataSourceFactory()

        val config: PagedList.Config = PagedList.Config.Builder().setEnablePlaceholders(true).setPageSize(10).build()

        val liveBuilder: LiveData<PagedList<UserModel>> = LivePagedListBuilder(factory, config).build()

        liveBuilder.observe(this, Observer { res: PagedList<UserModel> ->
            adp.submitList(res)
        })


    }

}