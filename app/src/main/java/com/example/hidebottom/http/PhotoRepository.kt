package com.example.hidebottom.http

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hidebottom.bean.CatBean
import com.example.hidebottom.bean.GrilBean
import com.example.hidebottom.bean.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/09/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class PhotoRepository private constructor(private val network: PhotoNetwork) {

    suspend fun getPictureSouce(
        key: String,
        q: String,
        per_page: Int,
        page: Int,
        pretty: Boolean
    ): CatBean {
        return withContext(Dispatchers.IO) {
            network.getPictureSouce(key, q, per_page, page, pretty)
        }
    }

    suspend fun getPictureGirl(): GrilBean {
        return withContext(Dispatchers.IO) {
            network.getPictureGirl()
        }
    }

    companion object {
        private lateinit var repository: PhotoRepository

        fun getInstance(network: PhotoNetwork): PhotoRepository {
            if (!Companion::repository.isInitialized) {
                synchronized(PhotoRepository::class.java) {
                    if (!Companion::repository.isInitialized) {
                        repository =
                            PhotoRepository(network)
                    }
                }
            }
            return repository
        }
    }
}