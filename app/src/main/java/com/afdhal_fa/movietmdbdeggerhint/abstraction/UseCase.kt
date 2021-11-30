package com.afdhal_fa.movietmdbdeggerhint.abstraction

interface UseCase<Params, out T> {
    suspend operator fun invoke(params: Params): T

    object None
}
