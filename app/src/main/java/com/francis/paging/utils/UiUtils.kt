package com.francis.paging.utils

import android.util.Log
import android.widget.Toast
import com.francis.paging.base.AppController

object UiUtils {


    internal fun log(TAG: String?, msg: String?) {
        Log.v("$TAG", "$msg")
    }

    internal fun errorLog(TAG: String?, msg: String?) {
        Log.e("$TAG", "$msg")
    }

    internal fun showToast(msg: String?) {
        Toast.makeText(AppController.instance, "$msg", Toast.LENGTH_SHORT).show()
    }
}