package com.afdhal_fa.movietmdbdeggerhint.domain.repository

import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import com.afdhal_fa.movietmdbdeggerhint.domain.entity.User

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

interface AccountRepository {
    suspend fun fetchUserDetail(name:String): Result<User>
}