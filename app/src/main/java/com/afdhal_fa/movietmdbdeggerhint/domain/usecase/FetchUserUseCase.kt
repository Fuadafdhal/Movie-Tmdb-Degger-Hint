package com.afdhal_fa.movietmdbdeggerhint.domain.usecase

import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result
import com.afdhal_fa.movietmdbdeggerhint.domain.entity.User
import com.afdhal_fa.movietmdbdeggerhint.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class FetchUserUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(name: String): Result<User> {
        return repository.fetchUserDetail(name)
    }
}