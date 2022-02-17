package com.example.hidebottom.util

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {


    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.d("NETWORK1", "网络连接成功")
    }

    //当访问的网络被阻塞或者解除阻塞时调用
    override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
        super.onBlockedStatusChanged(network, blocked)
        Log.d("NETWORK1", "BlockedStatusChanged")
    }

    //当网络连接属性发生变化时调用
    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties)
        Log.d("NETWORK1", "LinkPropertiesChanged")
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        Constant.isConnect = false
        Log.d("NETWORK1", "网络正在断开连接")
    }

    override fun onUnavailable() {
        super.onUnavailable()
        Log.d("NETWORK1", "网络连接超时或网络连接不可达")
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Constant.isConnect = false
        Log.d("NETWORK1", "网络已断开")
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            )
        ) {
            Constant.isConnect = true
            //判断是否连接上互联网并能与之通信
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) Log.d(
                "NETWORK1",
                "移动网络"
            )         //判断是移动网
            else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) Log.d(
                "NETWORK1",
                "wifi网络"
            )           //判断是wifi
            else Log.d("NETWORK1", "其它网络")
        } else Log.d("NETWORK1", "无网络")
    }

}