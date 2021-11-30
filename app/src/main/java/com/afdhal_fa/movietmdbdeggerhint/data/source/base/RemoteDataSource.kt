package com.afdhal_fa.movietmdbdeggerhint.data.source.base

import com.afdhal_fa.movietmdbdeggerhint.data.vo.HttpResult
import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class RemoteDataSource {

    companion object {
        private const val JSON_KEY_MESSAGE = "message"
        private const val JSON_KEY_STATUS = "status"
    }

    open suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val result = when (throwable.code()) {
                            in 400..451 -> parseHttpError(HttpResult.CLIENT_ERROR, throwable)
                            in 500..599 -> parseHttpError(HttpResult.SERVER_ERROR, throwable)
                            else -> error(
                                HttpResult.NOT_DEFINED,
                                throwable.code(),
                                "Undefined error",
                                null
                            )
                        }
                        result
                    }
                    is UnknownHostException -> error(
                        HttpResult.NO_CONNECTION,
                        null,
                        "No internet connection",
                        null
                    )
                    is SocketTimeoutException -> error(
                        HttpResult.TIMEOUT,
                        null,
                        "Slow connection",
                        null
                    )
                    is IOException -> error(HttpResult.BAD_RESPONSE, null, throwable.message, null)
                    else -> error(HttpResult.NOT_DEFINED, null, throwable.message, null)
                }
            }
        }
    }

    private fun parseHttpError(cause: HttpResult, throwable: HttpException): Result<Nothing> {
        return try {
            val errorResponse =
                throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
            val errorMessage = errorResponse.getOrNull(JSON_KEY_MESSAGE)
            val errorStatus = errorResponse.getOrNull(JSON_KEY_STATUS)
            error(cause, throwable.code(), errorMessage, errorStatus)
        } catch (exception: Exception) {
            error(cause, throwable.code(), exception.localizedMessage, null)
        }
    }

    private fun error(
        cause: HttpResult,
        code: Int?,
        errorMessage: String?,
        errorStatus: String?
    ): Result.Error {
        return Result.Error(cause, code, errorMessage, errorStatus)
    }

    @Suppress("SwallowedException")
    private fun String.getOrNull(key: String): String? {
        return try {
            val json = JSONObject(this)
            json.getString(key)
        } catch (e: JSONException) {
            null
        }
    }
}
