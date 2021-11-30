package com.afdhal_fa.movietmdbdeggerhint.data.source

import com.afdhal_fa.movietmdbdeggerhint.data.response.UserDto
import com.afdhal_fa.movietmdbdeggerhint.data.services.AccountServices
import com.afdhal_fa.movietmdbdeggerhint.data.source.base.RemoteDataSource
import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class AccountRemoteDataSource @Inject constructor(
    private val service: AccountServices
) : RemoteDataSource() {
    suspend fun fetchUser(
        dispatcher: CoroutineDispatcher,
        name: String,
    ): Result<UserDto> {
        return safeApiCall(dispatcher) { service.fetchUserDetail(name) }
    }
}