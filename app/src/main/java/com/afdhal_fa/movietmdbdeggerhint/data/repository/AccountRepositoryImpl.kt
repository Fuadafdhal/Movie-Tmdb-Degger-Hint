package com.afdhal_fa.movietmdbdeggerhint.data.repository

import com.afdhal_fa.movietmdbdeggerhint.data.dispatcher.DispatcherProvider
import com.afdhal_fa.movietmdbdeggerhint.data.mapper.UserMapper
import com.afdhal_fa.movietmdbdeggerhint.data.source.AccountRemoteDataSource
import com.afdhal_fa.movietmdbdeggerhint.data.util.extension.mapApiResultToDomain
import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import com.afdhal_fa.movietmdbdeggerhint.domain.entity.User
import com.afdhal_fa.movietmdbdeggerhint.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class AccountRepositoryImpl @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: AccountRemoteDataSource,
    private val userMapper: UserMapper,
) : AccountRepository {
    override suspend fun fetchUserDetail(name: String): Result<User> {
        val apiResult = remoteDataSource.fetchUser(dispatcher.io, name)
        return apiResult.mapApiResultToDomain(userMapper)
    }
}