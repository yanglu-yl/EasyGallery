package com.cryallen.wanlearning.ui.view.loadcallback

import com.example.hidebottom.R
import com.kingja.loadsir.callback.Callback

/**
 * 自定义错误显示页面
 *
 * @author vsh9p8q
 * @DATE 2021/9/14
 */
class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.view_net_error
    }
}