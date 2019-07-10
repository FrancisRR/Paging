package com.francis.paging.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.francis.paging.R
import com.francis.paging.base.AppController
import com.francis.paging.db.AppDatabase
import com.francis.paging.db.UserModel
import com.francis.paging.ui.paging.PagingActivity
import com.francis.paging.utils.UiUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG by lazy { MainActivity::class.java.simpleName }

    @set:Inject
    var roomDb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        AppController.Dagger.inJect(this)

        setRoomValue()
    }

    @OnClick(R.id.btP)
    public fun onClick(v: View) {
        when (v.id) {
            R.id.btP -> startActivity(Intent(this, PagingActivity::class.java))
        }
    }

    private fun setRoomValue() {
        GlobalScope.launch {
            if (roomDb?.appDao()!!.sizeOfDb() <= 0) {
                for (dd in 1..100) {
                    roomDb?.appDao()?.insertItem(UserModel(dd, "Francis", "$dd"))
                }
            }

            printDBValue()
        }
    }

    private fun printDBValue() {
        GlobalScope.launch {
            val res = roomDb?.appDao()?.getAll()!!
            for (data in res) {
                UiUtils.log(TAG, "${data.id}")
            }

        }
    }

}
