package com.example.hidebottom.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.hidebottom.util.InjectorUtil
import com.example.hidebottom.viewModel.PhotoViewModel

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/12/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseFragment<B : ViewBinding> : Fragment() {
    protected var view: B ?= null
    protected var inflater: LayoutInflater?= null
    protected var container: ViewGroup?= null

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater
        this.container = container
        view = setView()

        return view!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewCreat()
    }

    protected abstract fun setView(): B

    protected abstract fun onViewCreat()

    override fun onDestroyView() {
        super.onDestroyView()
        view = null
    }
}