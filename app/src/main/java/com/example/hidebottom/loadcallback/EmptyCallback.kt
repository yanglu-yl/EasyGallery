package com.cryallen.wanlearning.ui.view.loadcallback


import com.example.hidebottom.R
import com.kingja.loadsir.callback.Callback


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }
}