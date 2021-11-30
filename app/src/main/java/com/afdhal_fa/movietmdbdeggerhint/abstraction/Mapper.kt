package com.afdhal_fa.movietmdbdeggerhint.abstraction

interface Mapper<in I, out O> {
    fun map(input: I): O
}
