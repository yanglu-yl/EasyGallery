package com.example.hidebottom

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import com.cryallen.wanlearning.ui.view.loadcallback.EmptyCallback
import com.cryallen.wanlearning.ui.view.loadcallback.ErrorCallback
import com.cryallen.wanlearning.ui.view.loadcallback.LoadingCallback
import com.example.hidebottom.util.NetworkCallbackImpl
import com.kingja.loadsir.core.LoadSir

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/10/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()

        val networkCallback: NetworkCallbackImpl = NetworkCallbackImpl()
        val connMgr: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request: NetworkRequest = NetworkRequest.Builder().build()

        connMgr.registerNetworkCallback(request, networkCallback)

        instance = this

        LoadSir.beginBuilder()
            .addCallback(LoadingCallback()) //加载页面
            .addCallback(ErrorCallback())   //错误页面
            .addCallback(EmptyCallback())   //空页面
            .commit()
    }

    companion object {
        lateinit var instance: Application
    }
}