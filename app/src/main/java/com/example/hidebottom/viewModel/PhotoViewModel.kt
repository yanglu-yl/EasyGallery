package com.example.hidebottom.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hidebottom.bean.CatBean
import com.example.hidebottom.bean.GrilBean
import com.example.hidebottom.bean.Hit
import com.example.hidebottom.http.PhotoPagingSource
import com.example.hidebottom.http.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/09/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class PhotoViewModel(private val repository: PhotoRepository) : ViewModel() {

    var photoData = MutableLiveData<CatBean>()
    var grilData = MutableLiveData<GrilBean>()
    var retry: (() -> Any)? = null          //保存函数，可用来执行网络重试

    fun getPhoto(per_page: Int, page: Int): MutableLiveData<CatBean> {
        launch({
            retry = null
            photoData.postValue(repository.getPictureSouce(key, queryKey, per_page, page, true))
        }, {
            retry = { getPhoto(per_page, page) }
            Log.e("ERROR", it.message.toString())
        })
        return photoData
    }

    fun getPhotoGril(): MutableLiveData<GrilBean> {
        launch({
            retry = null
            grilData.postValue(repository.getPictureGirl())
        }, {
            retry = { getPhotoGril() }
            Log.e("ERROR", it.message.toString())
        })
        return grilData
    }

    var data = Pager(
        PagingConfig(
            pageSize = 50,
            enablePlaceholders = false,
            prefetchDistance = 3,
            initialLoadSize = 50,
            maxSize = 300
        )
    ) {
        PhotoPagingSource()
    }.flow
        .cachedIn(viewModelScope)

    fun searchPhoto(): Flow<PagingData<Hit>>{
        return data
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }

    companion object {
        private val key = "18138703-f8467438c77adc35dabe81fca"
        private val queryKey =
            arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal").random()
    }
}