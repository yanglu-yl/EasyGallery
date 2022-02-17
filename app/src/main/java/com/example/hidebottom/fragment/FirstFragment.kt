package com.example.hidebottom.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hidebottom.adapter.CatAdapter
import com.example.hidebottom.databinding.FragmentFirstBinding
import com.example.hidebottom.util.InjectorUtil
import com.example.hidebottom.viewModel.PhotoViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/10/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    private val viewModel: PhotoViewModel by viewModels<PhotoViewModel>(
        { requireActivity() },
        { InjectorUtil.getPhotoModel() })

    override fun setView(): FragmentFirstBinding {
        return FragmentFirstBinding.inflate(inflater!!, container, false)
    }

    override fun onViewCreat() {

        val madapter = CatAdapter()
        view?.reGallery?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        view?.reGallery?.adapter = madapter

        //viewModel.retry?.invoke()

        //livedata：观察者模式（观察者与被观察者建立一次连接），submitList监听到photoData（服务端的数据）的变化后继续执行observe的方法
        // 服务端photoData数据更新但是客户端并不知道photoData更新所以就需要客户端主动方去重新获取数据更新photoData
        /*viewModel.photoData.observe(viewLifecycleOwner, Observer {
            madapter.submitList(it.hits)
            swipeLayoutGallery.isRefreshing = false
        })

        viewModel.photoData.value ?: viewModel.getPhoto(50, 1)

        swipeLayoutGallery.setOnRefreshListener {
            viewModel.getPhoto(50, 1)     //数据更新的触动方
        }*/

        lifecycleScope.launch {
            viewModel.searchPhoto().collectLatest {
                view?.swipeLayoutGallery?.isRefreshing = false
                madapter.submitData(it)
            }
        }

        view?.swipeLayoutGallery?.setOnRefreshListener {
            madapter.refresh()
        }
        madapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.Error -> {
                    Log.d("TAG", "dddd")
                    val emptyView = view?.emptyView?.inflate()
                    emptyView?.visibility = View.VISIBLE
                    view?.reGallery?.visibility = View.GONE
                }
            }
        }
    }
}