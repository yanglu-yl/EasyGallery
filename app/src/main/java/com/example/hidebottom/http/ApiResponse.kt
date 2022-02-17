package com.example.hidebottom.http

import java.io.Serializable

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/12/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class ApiResponse<T>(
    open val data: T? = null,
    open val errorCode: Int? = null,
    open val errorMsg: String? = null,
    open val error: Throwable? = null,
) : Serializable {
    val isSuccess: Boolean
        get() = errorCode == 0
}

data class StartResponse<T>(val response: T) : ApiResponse<T>(data = response)

class SuccessResponse<T> : ApiResponse<T>()

data class EmptyResponse<T>(override val errorCode: Int?, override val errorMsg: String?) :
    ApiResponse<T>(errorCode = errorCode, errorMsg = errorMsg)

data class FailureResponse<T>(val throwable: Throwable) : ApiResponse<T>(error = throwable)
