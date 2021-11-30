package com.afdhal_fa.movietmdbdeggerhint.data.util.extension

import com.afdhal_fa.movietmdbdeggerhint.abstraction.Mapper
import com.afdhal_fa.movietmdbdeggerhint.data.vo.Result

fun <I, O> Result<I>.mapApiResultToDomain(mapper: Mapper<I, O>): Result<O> {
    return when (this) {
        is Result.Success -> Result.Success(mapper.map(this.data))
        is Result.Error -> Result.Error(this.cause, this.code, this.errorMessage, this.status)
        else -> Result.Error()
    }
}
