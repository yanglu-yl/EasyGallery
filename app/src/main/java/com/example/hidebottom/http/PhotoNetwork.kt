package com.example.hidebottom.http

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/09/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class PhotoNetwork {
    private val apiService by lazy { RetrofitClient.getInstance().create(ApiService::class.java) }

    suspend fun getPictureSouce(key: String, q: String, per_page: Int, page: Int, pretty: Boolean) =
        apiService.getPictureSouce(key, q, per_page, page, pretty)

    suspend fun getPictureGirl() = apiService.getPictureGirl()

    companion object {
        private var network: PhotoNetwork? = null

        fun getInstance(): PhotoNetwork {
            if (network == null) {
                synchronized(PhotoNetwork::class.java) {
                    if (network == null) {
                        network =
                            PhotoNetwork()
                    }
                }
            }
            return network!!
        }
    }
}