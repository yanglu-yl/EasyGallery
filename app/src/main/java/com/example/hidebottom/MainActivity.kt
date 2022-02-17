package com.example.hidebottom

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.hidebottom.util.Constant


class MainActivity : AppCompatActivity() {

    var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.fragment2))
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || findNavController(R.id.fragment2).navigateUp()
    }

    private fun messagevoid() {
        showLoadingDialog()
    }

    private fun showLoadingDialog() {
        alertDialog = AlertDialog.Builder(this).create()
        alertDialog?.setCancelable(false)
        alertDialog?.setOnKeyListener(object : DialogInterface.OnKeyListener {
            override fun onKey(dialog: DialogInterface?, keycode: Int, event: KeyEvent?): Boolean {
                if (keycode == KeyEvent.KEYCODE_SEARCH || keycode == KeyEvent.KEYCODE_BACK) {
                    return true
                }
                return false
            }
        })
        alertDialog?.show()
        alertDialog?.setContentView(R.layout.base_loading)
        alertDialog?.setCanceledOnTouchOutside(false)
    }

    fun dismissLoadingDialog() {
        if (alertDialog!!.isShowing) {
            alertDialog!!.dismiss()
        }
    }


    val HEART_BEAT_RATE: Long = 5 * 1000      //每隔5秒发送心跳
    var mHandler: Handler = Handler()

    var reciveBeatRunnable: Runnable = object : Runnable {
        override fun run() {

            if (Constant.isConnect) {
                dismissLoadingDialog()
            } else {
                Log.d("dddd", "无网络")
            }
            //每隔一定的时间，对服务端发送的数据检测
            mHandler.postDelayed(this, HEART_BEAT_RATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(reciveBeatRunnable)
    }


}